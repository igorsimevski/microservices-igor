package com.igor.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Locale;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseWrapperDto {
  public enum Type {
    STATUS, DATA, ERROR;

    @Override
    public String toString() {
      return super.toString().toLowerCase(Locale.ROOT);
    }
  }
  private Type type;
  private StatusDto status;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object data;
}
