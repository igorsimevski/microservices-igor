package com.igor.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(description = "API path invoked by client")
  private String apiPath;

  @Schema(description = "Time representing when the error happened")
  private LocalDateTime time;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(description = "Error message representing the error happened")
  private String description;
}
