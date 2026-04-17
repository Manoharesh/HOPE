package com.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column
    private LocalDate returnDate;

    @Column(nullable = false)
    private Double fineAmount = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status = LoanStatus.ACTIVE;

    public Loan() {}

    public Loan(Long id, Book book, Member member, LocalDate issueDate, LocalDate dueDate,
                LocalDate returnDate, Double fineAmount, LoanStatus status) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount != null ? fineAmount : 0.0;
        this.status = status != null ? status : LoanStatus.ACTIVE;
    }

    // --- Getters ---
    public Long getId() { return id; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public Double getFineAmount() { return fineAmount; }
    public LoanStatus getStatus() { return status; }

    // --- Setters ---
    public void setId(Long id) { this.id = id; }
    public void setBook(Book book) { this.book = book; }
    public void setMember(Member member) { this.member = member; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setFineAmount(Double fineAmount) { this.fineAmount = fineAmount; }
    public void setStatus(LoanStatus status) { this.status = status; }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Book book;
        private Member member;
        private LocalDate issueDate;
        private LocalDate dueDate;
        private LocalDate returnDate;
        private Double fineAmount = 0.0;
        private LoanStatus status = LoanStatus.ACTIVE;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder book(Book book) { this.book = book; return this; }
        public Builder member(Member member) { this.member = member; return this; }
        public Builder issueDate(LocalDate issueDate) { this.issueDate = issueDate; return this; }
        public Builder dueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }
        public Builder returnDate(LocalDate returnDate) { this.returnDate = returnDate; return this; }
        public Builder fineAmount(Double fineAmount) { this.fineAmount = fineAmount; return this; }
        public Builder status(LoanStatus status) { this.status = status; return this; }

        public Loan build() {
            return new Loan(id, book, member, issueDate, dueDate, returnDate, fineAmount, status);
        }
    }
}
