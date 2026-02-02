package com.igor.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public class ErrorResponseDto {

  @Schema(description = "API path invoked by client")
  private String apiPath;

  @Schema(description = "Error code representing the error", example = "404")
  private int code;

  @Schema(description = "Error message representing the error happened")
  private String message;

  @Schema(description = "Time representing when the error happened")
  private LocalDateTime time;
}
