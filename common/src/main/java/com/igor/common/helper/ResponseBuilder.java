package com.igor.common.helper;

import com.igor.common.constants.CommonConstants;
import com.igor.common.dto.ResponseWrapperDto;
import com.igor.common.dto.StatusDto;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
public class ResponseBuilder {

  private final String resourceName;

  public ResponseEntity<ResponseWrapperDto> createSuccess() {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(wrapStatus(CommonConstants.STATUS_201,
            String.format(CommonConstants.MESSAGE_201, resourceName)));
  }

  private ResponseWrapperDto wrapStatus(String code, String message) {
    return ResponseWrapperDto.builder()
        .type(ResponseWrapperDto.Type.STATUS)
        .status(StatusDto.builder().code(code).message(message).build())
        .build();
  }

  public <T> ResponseEntity<ResponseWrapperDto> fetchSuccess(T response) {
    return ResponseEntity.ok(ResponseWrapperDto.builder()
        .type(ResponseWrapperDto.Type.DATA)
        .data(response)
        .build());
  }

  public ResponseEntity<ResponseWrapperDto> responseSuccess() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            wrapStatus(
                CommonConstants.STATUS_200,
                CommonConstants.MESSAGE_200));
  }

  public ResponseEntity<ResponseWrapperDto> updateFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(
            wrapStatus(
                CommonConstants.STATUS_417,
                CommonConstants.MESSAGE_417_UPDATE));
  }

  public ResponseEntity<ResponseWrapperDto> deleteFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(
            wrapStatus(
                CommonConstants.STATUS_417,
                CommonConstants.MESSAGE_417_UPDATE));
  }
}
