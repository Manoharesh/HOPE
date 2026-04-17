package com.library.repository;

import com.library.model.Loan;
import com.library.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(LoanStatus status);

    List<Loan> findByMemberId(Long memberId);

    Optional<Loan> findByBookIdAndMemberIdAndStatus(Long bookId, Long memberId, LoanStatus status);

    List<Loan> findByStatusIn(List<LoanStatus> statuses);
}
