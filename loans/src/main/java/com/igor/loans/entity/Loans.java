package com.igor.loans.entity;

import com.igor.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loans extends BaseEntity {
  @Id
  private Long loanId;
  private String mobileNumber;
  private String loanNumber;
  private String loanType;
  private int totalLoan;
  private int amountPaid;
  private int outstandingAmount;
}
