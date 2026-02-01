package com.igor.loans.controller;

import com.igor.common.constants.CommonConstants;
import com.igor.common.dto.ResponseDto;
import com.igor.loans.dto.LoansDto;
import com.igor.loans.service.LoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

  private static final String RESOURCE_NAME_LOAN = "Loan";
  private LoansService loansService;

  @PostMapping("create")
  public ResponseEntity<ResponseDto> createLoan(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
      String mobileNumber) {
    loansService.createLoan(mobileNumber);
    return ResponseEntity.ok(ResponseDto.builder()
        .statusCode(CommonConstants.STATUS_201)
        .statusMsg(String.format(CommonConstants.MESSAGE_201, RESOURCE_NAME_LOAN))
        .build());
  }

  @GetMapping("fetch")
  public ResponseEntity<LoansDto> fetchLoan(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
      String mobileNumber) {
    return ResponseEntity.ok(loansService.fetchLoan(mobileNumber));
  }

  @PutMapping("update")
  public ResponseEntity<ResponseDto> updateLoan(
      @Valid @RequestBody LoansDto loansDto) {
    boolean isUpdated = loansService.updateLoan(loansDto);
    if (isUpdated) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(ResponseDto.builder()
              .statusCode(CommonConstants.STATUS_200)
              .statusMsg(CommonConstants.MESSAGE_200)
              .build());
    } else {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(ResponseDto.builder()
              .statusCode(CommonConstants.STATUS_417)
              .statusMsg(CommonConstants.MESSAGE_417_UPDATE)
              .build());
    }
  }

  @DeleteMapping("delete")
  public ResponseEntity<ResponseDto> deleteLoan(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
      String mobileNumber) {
    boolean isDeleted = loansService.deleteLoan(mobileNumber);
    if (isDeleted) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(ResponseDto.builder()
              .statusCode(CommonConstants.STATUS_200)
              .statusMsg(CommonConstants.MESSAGE_200)
              .build());
    } else {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(ResponseDto.builder()
              .statusCode(CommonConstants.STATUS_417)
              .statusMsg(CommonConstants.MESSAGE_417_DELETE)
              .build());
    }
  }
}
