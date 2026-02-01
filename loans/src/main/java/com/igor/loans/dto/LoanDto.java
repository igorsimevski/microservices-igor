package com.igor.loans.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanDto {
  private Long loanId;
  private String mobileNumber;
  private String loanNumber;
  private String loanType;
  private double totalLoan;
  private double amountPaid;
  private double outstandingAmount;
}
