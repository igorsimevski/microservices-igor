package com.igor.loans.controller;

import com.igor.common.constants.CommonConstants;
import com.igor.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoansResponse {

  private static final String RESOURCE_NAME_LOAN = "Loan";

  private LoansResponse() {
    throw new IllegalStateException("Utility class");
  }

  static ResponseEntity<ResponseDto> createLoanSuccess() {
    return ResponseEntity.ok(ResponseDto.builder()
        .statusCode(CommonConstants.STATUS_201)
        .statusMsg(String.format(CommonConstants.MESSAGE_201, RESOURCE_NAME_LOAN))
        .build());
  }

  static ResponseEntity<ResponseDto> responseSuccess() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDto.builder()
            .statusCode(CommonConstants.STATUS_200)
            .statusMsg(CommonConstants.MESSAGE_200)
            .build());
  }

  static ResponseEntity<ResponseDto> updateLoanFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(ResponseDto.builder()
            .statusCode(CommonConstants.STATUS_417)
            .statusMsg(CommonConstants.MESSAGE_417_UPDATE)
            .build());
  }

  static ResponseEntity<ResponseDto> deleteLoanFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(ResponseDto.builder()
            .statusCode(CommonConstants.STATUS_417)
            .statusMsg(CommonConstants.MESSAGE_417_DELETE)
            .build());
  }
}
