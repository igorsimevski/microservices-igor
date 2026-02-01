package com.igor.loans.controller;

import com.igor.common.dto.ResponseDto;
import com.igor.loans.dto.LoansDto;
import com.igor.loans.service.LoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
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

  private static final String VALIDATION_10_DIGIT_REGEX = "(^$|\\d{10})";
  private static final String VALIDATION_10_DIGIT_NUMBER = "Mobile number must be 10 digits";
  private static final ResponseBuilder responseBuilder = ResponseBuilder.builder()
      .resourceName("Loan")
      .build();
  private LoansService loansService;

  @PostMapping("create")
  public ResponseEntity<ResponseDto> createLoan(
      @RequestParam
      @Pattern(regexp = VALIDATION_10_DIGIT_REGEX, message = VALIDATION_10_DIGIT_NUMBER)
      String mobileNumber) {
    loansService.createLoan(mobileNumber);
    return responseBuilder.createSuccess();
  }

  @GetMapping("fetch")
  public ResponseEntity<LoansDto> fetchLoan(
      @RequestParam
      @Pattern(regexp = VALIDATION_10_DIGIT_REGEX, message = VALIDATION_10_DIGIT_NUMBER)
      String mobileNumber) {
    return responseBuilder.fetchSuccess(loansService.fetchLoan(mobileNumber));
  }

  @PutMapping("update")
  public ResponseEntity<ResponseDto> updateLoan(
      @Valid @RequestBody LoansDto loansDto) {
    boolean isUpdated = loansService.updateLoan(loansDto);
    if (isUpdated) {
      return responseBuilder.responseSuccess();
    } else {
      return responseBuilder.updateFailure();
    }
  }

  @DeleteMapping("delete")
  public ResponseEntity<ResponseDto> deleteLoan(
      @RequestParam
      @Pattern(regexp = VALIDATION_10_DIGIT_REGEX, message = VALIDATION_10_DIGIT_NUMBER)
      String mobileNumber) {
    boolean isDeleted = loansService.deleteLoan(mobileNumber);
    if (isDeleted) {
      return responseBuilder.responseSuccess();
    } else {
      return responseBuilder.deleteFailure();
    }
  }
}
