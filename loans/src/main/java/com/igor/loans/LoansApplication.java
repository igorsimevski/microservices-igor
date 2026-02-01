package com.igor.loans;

import static com.igor.common.constants.CommonConstants.AUDIT;
import static com.igor.common.constants.CommonConstants.EXCEPTION;
import static com.igor.common.constants.CommonConstants.LOANS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {LOANS, AUDIT, EXCEPTION})
public class LoansApplication {
  public static void main(String[] args) {
    SpringApplication.run(LoansApplication.class, args);
  }
}
