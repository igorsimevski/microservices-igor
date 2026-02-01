package com.igor.loans.controller;

import com.igor.common.constants.CommonConstants;
import com.igor.common.dto.ResponseDto;
import com.igor.loans.dto.LoansDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

  private static final String RESOURCE_NAME_LOAN = "Loan";

  @PostMapping("create")
  public ResponseEntity<ResponseDto> createLoan() {
    return ResponseEntity.ok(ResponseDto.builder()
        .statusCode(CommonConstants.STATUS_201)
        .statusMsg(String.format(CommonConstants.MESSAGE_201, RESOURCE_NAME_LOAN))
        .build());
  }

  @GetMapping("fetch")
  public ResponseEntity<LoansDto> fetchLoan() {
    return ResponseEntity.ok(LoansDto.builder()
        .build());
  }

  @PutMapping("update")
  public ResponseEntity<ResponseDto> updateLoan() {
    return ResponseEntity.ok(ResponseDto.builder()
        .statusCode(CommonConstants.STATUS_200)
        .statusMsg(CommonConstants.MESSAGE_200)
        .build());
  }

  @DeleteMapping("delete")
  public ResponseEntity<ResponseDto> deleteLoan() {
    return ResponseEntity.ok(ResponseDto.builder()
        .statusCode(CommonConstants.STATUS_200)
        .statusMsg(CommonConstants.MESSAGE_200)
        .build());
  }
}
