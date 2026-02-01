package com.igor.loans.repository;

import com.igor.loans.entity.Loans;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
  Optional<Loans> findByMobileNumber(String mobileNumber);
}
