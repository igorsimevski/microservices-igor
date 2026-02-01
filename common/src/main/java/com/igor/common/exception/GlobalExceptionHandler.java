package com.igor.common.exception;

import com.igor.common.dto.ErrorResponseDto;
import com.igor.common.dto.ResponseWrapperDto;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    Map<String, String> validationErrors = new HashMap<>();
    List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
    validationErrorList.forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String validationMsg = error.getDefaultMessage();
      validationErrors.put(fieldName, validationMsg);
    });
    return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseWrapperDto> handleGlobalException(
      Exception exception,
      WebRequest webRequest) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, webRequest, exception);
  }

  private static ResponseEntity<ResponseWrapperDto> buildErrorResponse(
      HttpStatus httpStatus,
      WebRequest webRequest,
      Exception exception) {
    return new ResponseEntity<>(ResponseWrapperDto.builder()
        .type(ResponseWrapperDto.Type.ERROR)
        .error(ErrorResponseDto.builder()
            .apiPath(webRequest.getDescription(false))
            .code(httpStatus.value())
            .codeDescription(httpStatus)
            .message(exception.getMessage())
            .time(LocalDateTime.now())
            .build())
        .build(),
        httpStatus);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResponseWrapperDto> handleResourceNotFoundException(
      ResourceNotFoundException exception,
      WebRequest webRequest) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, webRequest, exception);
  }

  @ExceptionHandler(ResourceExistsException.class)
  public ResponseEntity<ResponseWrapperDto> handleCustomerAlreadyExistsException(
      ResourceExistsException exception,
      WebRequest webRequest) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST, webRequest, exception);
  }
}
