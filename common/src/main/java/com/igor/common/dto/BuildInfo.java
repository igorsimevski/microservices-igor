package com.igor.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class BuildInfo {
  private final String version;
  private final String javaVersion;
  private final String javaHome;
}
