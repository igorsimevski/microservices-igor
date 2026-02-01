package com.igor.loans.mapper;

import com.igor.loans.dto.LoansDto;
import com.igor.loans.entity.Loans;

public class LoanMapper {
  private LoanMapper() {
    throw new IllegalStateException("Utility class");
  }

  public static LoansDto mapToLoanDto(Loans loans) {
    return LoansDto.builder()
        .loanId(loans.getLoanId())
        .mobileNumber(loans.getMobileNumber())
        .loanNumber(loans.getLoanNumber())
        .loanType(loans.getLoanType())
        .totalLoan(loans.getTotalLoan())
        .amountPaid(loans.getAmountPaid())
        .outstandingAmount(loans.getOutstandingAmount())
        .build();
  }

  public static Loans mapToLoan(String mobileNumber) {
    return Loans.builder()
        .mobileNumber(mobileNumber)
        .loanType("Personal Loan")
        .loanNumber("LN" + System.currentTimeMillis())
        .totalLoan(500000)
        .amountPaid(0)
        .outstandingAmount(500000)
        .build();
  }

  public static Loans mapToLoan(LoansDto loansDto, Loans loan) {
    loan.setMobileNumber(loansDto.getMobileNumber());
    loan.setLoanNumber(loansDto.getLoanNumber());
    loan.setLoanType(loansDto.getLoanType());
    loan.setTotalLoan(loansDto.getTotalLoan());
    loan.setAmountPaid(loansDto.getAmountPaid());
    loan.setOutstandingAmount(loansDto.getOutstandingAmount());
    return loan;
  }
}
