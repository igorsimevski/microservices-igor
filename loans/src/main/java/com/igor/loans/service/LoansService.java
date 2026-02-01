package com.igor.loans.service;

import com.igor.loans.dto.LoansDto;

public interface LoansService {

  void createLoan(LoansDto loanDto);
  LoansDto fetchLoan(String mobileNumber);
  boolean updateLoan(LoansDto loansDto);
  boolean deleteLoan(String mobileNumber);
}
