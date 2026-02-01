package com.igor.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoansDto {
  private Long loanId;
  private String mobileNumber;
  private String loanNumber;
  private String loanType;
  private int totalLoan;
  private int amountPaid;
  private int outstandingAmount;
}
