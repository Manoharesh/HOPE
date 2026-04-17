package com.library.config;

import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.LoanStatus;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    CommandLineRunner seedDatabase(BookRepository bookRepository,
                                   MemberRepository memberRepository,
                                   LoanRepository loanRepository) {
        return args -> {
            log.info("=== Seeding initial library data ===");

            // --- Seed Books ---
            Book b1 = bookRepository.save(Book.builder().title("Clean Code").author("Robert C. Martin").category("Programming").totalCopies(3).availableCopies(3).build());
            Book b2 = bookRepository.save(Book.builder().title("The Pragmatic Programmer").author("David Thomas").category("Programming").totalCopies(2).availableCopies(2).build());
            bookRepository.save(Book.builder().title("Design Patterns").author("Gang of Four").category("Software Architecture").totalCopies(2).availableCopies(2).build());
            bookRepository.save(Book.builder().title("Sapiens").author("Yuval Noah Harari").category("History").totalCopies(4).availableCopies(4).build());
            bookRepository.save(Book.builder().title("Atomic Habits").author("James Clear").category("Self-Help").totalCopies(5).availableCopies(5).build());

            log.info("Seeded {} books.", bookRepository.count());

            // --- Seed Members ---
            Member m1 = memberRepository.save(Member.builder().name("Alice Johnson").email("alice@example.com").phoneNumber("9876543210").totalPendingFines(0.0).build());
            Member m2 = memberRepository.save(Member.builder().name("Bob Smith").email("bob@example.com").phoneNumber("8765432109").totalPendingFines(0.0).build());
            memberRepository.save(Member.builder().name("Carol White").email("carol@example.com").phoneNumber("7654321098").totalPendingFines(0.0).build());

            log.info("Seeded {} members.", memberRepository.count());

            // --- Active Loan for Alice (not overdue) ---
            b1.setAvailableCopies(b1.getAvailableCopies() - 1);
            bookRepository.save(b1);
            loanRepository.save(Loan.builder()
                    .book(b1).member(m1)
                    .issueDate(LocalDate.now().minusDays(5))
                    .dueDate(LocalDate.now().plusDays(9))
                    .status(LoanStatus.ACTIVE)
                    .fineAmount(0.0)
                    .build());

            // --- Returned overdue loan for Bob (fine = 5 * $1.00 = $5.00) ---
            b2.setAvailableCopies(b2.getAvailableCopies() - 1);
            bookRepository.save(b2);
            double fine = 5 * 1.0;
            m2.setTotalPendingFines(m2.getTotalPendingFines() + fine);
            memberRepository.save(m2);
            loanRepository.save(Loan.builder()
                    .book(b2).member(m2)
                    .issueDate(LocalDate.now().minusDays(21))
                    .dueDate(LocalDate.now().minusDays(7))
                    .returnDate(LocalDate.now().minusDays(2))
                    .status(LoanStatus.RETURNED)
                    .fineAmount(fine)
                    .build());

            log.info("Seeded {} loans.", loanRepository.count());
            log.info("=== Data seeding complete ===");
        };
    }
}
