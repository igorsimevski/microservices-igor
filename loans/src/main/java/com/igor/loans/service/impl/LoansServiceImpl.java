package com.igor.loans.service.impl;

import com.igor.common.exception.ResourceExistsException;
import com.igor.common.exception.ResourceNotFoundException;
import com.igor.loans.dto.LoansDto;
import com.igor.loans.entity.Loans;
import com.igor.loans.mapper.LoanMapper;
import com.igor.loans.repository.LoansRepository;
import com.igor.loans.service.LoansService;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements LoansService {
  private static final Random RANDOM = new Random();
  private static final String RESOURCE_NAME_LOAN = "Loan";
  private LoansRepository loansRepository;

  @Override
  public void createLoan(String mobileNumber) {
    Optional<Loans> optionalLoan =
        loansRepository.findByMobileNumber(mobileNumber);
    if (optionalLoan.isPresent()) {
      throw new ResourceExistsException(
          "Loan already registered with given mobileNumber " + mobileNumber);
    }
    Loans loan = LoanMapper.mapToLoan(mobileNumber);
    loan.setLoanId(1000000000L + RANDOM.nextInt(900000000));
    loansRepository.save(loan);
  }

  @Override
  public LoansDto fetchLoan(String mobileNumber) {
    Loans loans = loansRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME_LOAN, "mobileNumber", mobileNumber));
    return LoanMapper.mapToLoanDto(loans);
  }

  @Override
  public boolean updateLoan(LoansDto loanDto) {
    return true;
  }

  @Override
  public boolean deleteLoan(String mobileNumber) {
    return true;
  }
}
