package com.igor.common.helper;

import static com.igor.common.constants.CommonConstants.MESSAGE_200;
import static com.igor.common.constants.CommonConstants.MESSAGE_201;
import static com.igor.common.constants.CommonConstants.MESSAGE_417_DELETE;
import static com.igor.common.constants.CommonConstants.MESSAGE_417_UPDATE;
import static com.igor.common.constants.CommonConstants.STATUS_200;
import static com.igor.common.constants.CommonConstants.STATUS_201;
import static com.igor.common.constants.CommonConstants.STATUS_417;

import com.igor.common.dto.ResponseWrapperDto;
import com.igor.common.dto.StatusDto;
import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
public class ResponseBuilder {

  private final String resourceName;

  public ResponseEntity<ResponseWrapperDto> createSuccess() {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(wrapStatus(STATUS_201, String.format(MESSAGE_201, resourceName)));
  }

  private ResponseWrapperDto wrapStatus(int code, String message) {
    return ResponseWrapperDto.builder()
        .type(ResponseWrapperDto.Type.STATUS)
        .status(StatusDto.builder()
            .code(code)
            .message(message)
            .time(LocalDateTime.now())
            .build())
        .build();
  }

  public <T> ResponseEntity<ResponseWrapperDto> fetchSuccess(T response) {
    return ResponseEntity.ok(ResponseWrapperDto.builder()
        .status(StatusDto.builder()
            .code(STATUS_200)
            .message(MESSAGE_200)
            .time(LocalDateTime.now())
            .build())
        .type(ResponseWrapperDto.Type.DATA)
        .data(response)
        .build());
  }

  public ResponseEntity<ResponseWrapperDto> responseSuccess() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(wrapStatus(STATUS_200, MESSAGE_200));
  }

  public ResponseEntity<ResponseWrapperDto> updateFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(wrapStatus(STATUS_417, MESSAGE_417_UPDATE));
  }

  public ResponseEntity<ResponseWrapperDto> deleteFailure() {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(wrapStatus(STATUS_417, MESSAGE_417_DELETE));
  }
}
