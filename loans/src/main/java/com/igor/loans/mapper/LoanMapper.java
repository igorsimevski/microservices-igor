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

  public static Loans mapToLoan(LoansDto loansDto) {
    return Loans.builder()
        .mobileNumber(loansDto.getMobileNumber())
        .loanNumber(loansDto.getLoanNumber())
        .loanType(loansDto.getLoanType())
        .totalLoan(loansDto.getTotalLoan())
        .amountPaid(loansDto.getAmountPaid())
        .outstandingAmount(loansDto.getOutstandingAmount())
        .build();
  }
}
