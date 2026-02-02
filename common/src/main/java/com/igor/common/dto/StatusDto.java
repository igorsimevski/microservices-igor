package com.igor.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold successful response information")
@Data
@Builder
public class StatusDto {

  @Schema(description = "Status code in the response")
  private int code;

  @Schema(description = "Status message in the response")
  private String message;
}
