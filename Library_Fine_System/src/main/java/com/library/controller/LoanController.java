package com.library.controller;

import com.library.model.Loan;
import com.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Borrow a book.
     * Request body: { "bookId": 1, "memberId": 1 }
     */
    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrowBook(@RequestBody Map<String, Long> request) {
        Long bookId = request.get("bookId");
        Long memberId = request.get("memberId");
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.borrowBook(bookId, memberId));
    }

    /**
     * Return a book.
     * Request body: { "loanId": 1 }
     */
    @PostMapping("/return")
    public ResponseEntity<Loan> returnBook(@RequestBody Map<String, Long> request) {
        Long loanId = request.get("loanId");
        return ResponseEntity.ok(loanService.returnBook(loanId));
    }

    /** Get all active and overdue loans. */
    @GetMapping
    public ResponseEntity<List<Loan>> getActiveLoans() {
        return ResponseEntity.ok(loanService.getActiveAndOverdueLoans());
    }

    /** Get full loan history. */
    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    /** Get all loans for a specific member. */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Loan>> getLoansByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(loanService.getLoansByMember(memberId));
    }

    /** Manually trigger overdue status update. */
    @PostMapping("/update-overdue")
    public ResponseEntity<String> updateOverdue() {
        loanService.updateOverdueStatuses();
        return ResponseEntity.ok("Overdue statuses updated successfully.");
    }
}
