package com.igor.loans.service;

import com.igor.loans.dto.LoanDto;

public interface LoansService {

  void createLoan(LoanDto loanDto);
  LoanDto fetchLoan(String mobileNumber);
  boolean updateLoan(LoanDto loanDto);
  boolean deleteLoan(String mobileNumber);
}
