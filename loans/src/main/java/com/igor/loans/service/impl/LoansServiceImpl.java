package com.igor.loans.service.impl;

import com.igor.loans.dto.LoanDto;
import com.igor.loans.repository.LoansRepository;
import com.igor.loans.service.LoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements LoansService {
  private LoansRepository loansRepository;

  @Override
  public void createLoan(LoanDto loanDto) {
  }

  @Override
  public LoanDto fetchLoan(String mobileNumber) {
    return LoanDto.builder().loanType("Igor Test").build();
  }

  @Override
  public boolean updateLoan(LoanDto loanDto) {
    return true;
  }

  @Override
  public boolean deleteLoan(String mobileNumber) {
    return true;
  }
}
