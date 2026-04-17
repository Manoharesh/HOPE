package com.library.service;

import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.exception.LoanNotFoundException;
import com.library.exception.MemberNotFoundException;
import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.LoanStatus;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanService {

    private static final int LOAN_PERIOD_DAYS = 14;
    private static final double DAILY_FINE_RATE = 1.0;

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanService(LoanRepository loanRepository,
                       BookRepository bookRepository,
                       MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Loan borrowBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException(bookId);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(LOAN_PERIOD_DAYS);

        Loan loan = Loan.builder()
                .book(book)
                .member(member)
                .issueDate(issueDate)
                .dueDate(dueDate)
                .status(LoanStatus.ACTIVE)
                .fineAmount(0.0)
                .build();

        return loanRepository.save(loan);
    }

    @Transactional
    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new IllegalStateException("This loan (ID: " + loanId + ") has already been returned.");
        }

        LocalDate returnDate = LocalDate.now();
        loan.setReturnDate(returnDate);

        // Fine calculation per rules:
        // - returned ON or BEFORE dueDate → fine = 0.0
        // - returned AFTER dueDate → fine = overdueDays * DAILY_FINE_RATE
        double fineAmount = 0.0;
        if (returnDate.isAfter(loan.getDueDate())) {
            long overdueDays = ChronoUnit.DAYS.between(loan.getDueDate(), returnDate);
            fineAmount = overdueDays * DAILY_FINE_RATE;
        }

        loan.setFineAmount(fineAmount);
        loan.setStatus(LoanStatus.RETURNED);

        Member member = loan.getMember();
        member.setTotalPendingFines(member.getTotalPendingFines() + fineAmount);
        memberRepository.save(member);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<Loan> getActiveAndOverdueLoans() {
        return loanRepository.findByStatusIn(List.of(LoanStatus.ACTIVE, LoanStatus.OVERDUE));
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getLoansByMember(Long memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    @Transactional
    public void updateOverdueStatuses() {
        List<Loan> activeLoans = loanRepository.findByStatus(LoanStatus.ACTIVE);
        LocalDate today = LocalDate.now();
        activeLoans.forEach(loan -> {
            if (today.isAfter(loan.getDueDate())) {
                loan.setStatus(LoanStatus.OVERDUE);
                loanRepository.save(loan);
            }
        });
    }
}
