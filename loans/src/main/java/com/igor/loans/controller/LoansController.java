package com.igor.loans.controller;

import static com.igor.loans.controller.LoansResponse.createLoanSuccess;
import static com.igor.loans.controller.LoansResponse.deleteLoanFailure;
import static com.igor.loans.controller.LoansResponse.responseSuccess;
import static com.igor.loans.controller.LoansResponse.updateLoanFailure;

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

  private static final String VALIDATION_10_DIGIT_NUMBER = "Mobile number must be 10 digits";
  private LoansService loansService;

  @PostMapping("create")
  public ResponseEntity<ResponseDto> createLoan(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = VALIDATION_10_DIGIT_NUMBER)
      String mobileNumber) {
    loansService.createLoan(mobileNumber);
    return ResponseEntity.ok(createLoanSuccess());
  }

  @GetMapping("fetch")
  public ResponseEntity<LoansDto> fetchLoan(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = VALIDATION_10_DIGIT_NUMBER)
      String mobileNumber) {
    return ResponseEntity.ok(loansService.fetchLoan(mobileNumber));
  }

  @PutMapping("update")
  public ResponseEntity<ResponseDto> updateLoan(
      @Valid @RequestBody LoansDto loansDto) {
    boolean isUpdated = loansService.updateLoan(loansDto);
    if (isUpdated) {
      return responseSuccess();
    } else {
      return updateLoanFailure();
    }
  }

  @DeleteMapping("delete")
  public ResponseEntity<ResponseDto> deleteLoan(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = VALIDATION_10_DIGIT_NUMBER)
      String mobileNumber) {
    boolean isDeleted = loansService.deleteLoan(mobileNumber);
    if (isDeleted) {
      return responseSuccess();
    } else {
      return deleteLoanFailure();
    }
  }
}
