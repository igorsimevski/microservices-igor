package com.igor.accounts.mapper;

import com.igor.accounts.dto.CustomerDto;
import com.igor.accounts.entity.Customer;

public final class CustomerMapper {

  private CustomerMapper() {
    throw new IllegalStateException("Utility class");
  }

  public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
    customerDto.setName(customer.getName());
    customerDto.setEmail(customer.getEmail());
    customerDto.setMobileNumber(customer.getMobileNumber());
    return customerDto;
  }

  public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
    customer.setName(customerDto.getName());
    customer.setEmail(customerDto.getEmail());
    customer.setMobileNumber(customerDto.getMobileNumber());
    return customer;
  }
}
