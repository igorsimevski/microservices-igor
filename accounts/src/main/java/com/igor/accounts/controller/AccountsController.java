package com.igor.accounts.controller;

import com.igor.accounts.dto.CustomerDto;
import com.igor.accounts.service.IAccountsService;
import com.igor.common.dto.ResponseWrapperDto;
import com.igor.common.helper.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "CRUD REST APIs for Accounts",
    description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

  private final IAccountsService accountsService;

  public AccountsController(IAccountsService accountsService) {
    this.accountsService = accountsService;
  }

  private static final ResponseBuilder responseBuilder = ResponseBuilder.builder()
      .resourceName("Account")
      .build();

  @Value("${build.version}")
  private String buildVersion;

  @Autowired
  private Environment environment;

  @Operation(
      summary = "Create Account REST API",
      description = "REST API to create new Customer & Account"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "201",
          description = "HTTP Status CREATED"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseWrapperDto.class)
          ))
  })
  @PostMapping("create")
  public ResponseEntity<ResponseWrapperDto> createAccount(
      @Valid @RequestBody CustomerDto customerDto) {
    accountsService.createAccount(customerDto);
    return responseBuilder.createSuccess();
  }

  @Operation(
      summary = "Fetch Account Details REST API",
      description = "REST API to fetch Customer &  Account details based on a mobile number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseWrapperDto.class)
          )
      )
  }
  )
  @GetMapping("fetch")
  public ResponseEntity<ResponseWrapperDto> fetchAccountDetails(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
      String mobileNumber) {
    CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
    return responseBuilder.fetchSuccess(customerDto);
  }

  @Operation(
      summary = "Update Account Details REST API",
      description = "REST API to update Customer &  Account details based on a account number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseWrapperDto.class)
          )
      )
  }
  )
  @PutMapping("update")
  public ResponseEntity<ResponseWrapperDto> updateAccountDetails(
      @Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = accountsService.updateAccount(customerDto);
    if (isUpdated) {
      return responseBuilder.responseSuccess();
    } else {
      return responseBuilder.updateFailure();
    }
  }

  @Operation(
      summary = "Delete Account & Customer Details REST API",
      description = "REST API to delete Customer &  Account details based on a mobile number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseWrapperDto.class)
          )
      )
  }
  )
  @DeleteMapping("delete")
  public ResponseEntity<ResponseWrapperDto> deleteAccountDetails(
      @RequestParam
      @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
      String mobileNumber) {
    boolean isDeleted = accountsService.deleteAccount(mobileNumber);
    if (isDeleted) {
      return responseBuilder.responseSuccess();
    } else {
      return responseBuilder.deleteFailure();
    }
  }

  @Operation(
      summary = "Get Build Info",
      description = "Get the current build info deployed for the Accounts Service"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseWrapperDto.class)
          )
      )
  }
  )
  @GetMapping("build-info")
  public ResponseEntity<ResponseWrapperDto> buildInfo() {
    return responseBuilder.fetchSuccess(buildVersion);
  }
  @GetMapping("java-version")
  public ResponseEntity<ResponseWrapperDto> javaVersion() {
    return responseBuilder.fetchSuccess(environment.getProperty("java.version"));
  }
}
