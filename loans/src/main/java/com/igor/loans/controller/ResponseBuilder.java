package com.igor.loans.controller;

import com.igor.common.constants.CommonConstants;
import com.igor.common.dto.ResponseDto;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
public class ResponseBuilder {

  private final String resourceName;


  private ResponseBuilder() {
    throw new IllegalStateException("Utility class");
  }

  ResponseEntity<ResponseDto> createSuccess() {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ResponseDto.builder()
            .statusCode(CommonConstants.STATUS_201)
            .statusMsg(String.format(CommonConstants.MESSAGE_201, resourceName))
            .build());
  }

  <T> ResponseEntity<T> fetchSuccess(T response) {
    return ResponseEntity.ok(response);
  }

  ResponseEntity<ResponseDto> responseSuccess() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDto.builder()
            .statusCode(CommonConstants.STATUS_200)
            .statusMsg(CommonConstants.MESSAGE_200)
            .build());
  }

  ResponseEntity<ResponseDto> updateFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(ResponseDto.builder()
            .statusCode(CommonConstants.STATUS_417)
            .statusMsg(CommonConstants.MESSAGE_417_UPDATE)
            .build());
  }

  ResponseEntity<ResponseDto> deleteFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(ResponseDto.builder()
            .statusCode(CommonConstants.STATUS_417)
            .statusMsg(CommonConstants.MESSAGE_417_DELETE)
            .build());
  }
}
