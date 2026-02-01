package com.igor.loans.repository;

import com.igor.loans.entity.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<Loan, Long> {
  Optional<Loan> findByMobileNumber(String mobileNumber);
}
