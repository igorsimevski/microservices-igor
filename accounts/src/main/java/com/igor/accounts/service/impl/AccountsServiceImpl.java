package com.igor.accounts.service.impl;

import com.igor.accounts.dto.AccountsDto;
import com.igor.accounts.dto.CustomerDto;
import com.igor.accounts.entity.Accounts;
import com.igor.accounts.entity.Customer;
import com.igor.accounts.mapper.AccountsMapper;
import com.igor.accounts.mapper.CustomerMapper;
import com.igor.accounts.repository.AccountsRepository;
import com.igor.accounts.repository.CustomerRepository;
import com.igor.accounts.service.IAccountsService;
import com.igor.common.constants.CommonConstants;
import com.igor.common.exception.ResourceExistsException;
import com.igor.common.exception.ResourceNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private static final String RESOURCE_NAME_CUSTOMER = "Customer";
  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;

  /**
   * @param customerDto - CustomerDto Object
   */
  @Override
  public void createAccount(CustomerDto customerDto) {
    throwIfCustomerExists(customerDto);
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer));
  }

  private void throwIfCustomerExists(CustomerDto customerDto) {
    Optional<Customer> optionalCustomer =
        customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new ResourceExistsException(
          "Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
    }
  }

  /**
   * @param customer - Customer Object
   * @return the new account details
   */
  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + CommonConstants.RANDOM.nextInt(900000000);
    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(CommonConstants.SAVINGS);
    newAccount.setBranchAddress(CommonConstants.ADDRESS);
    return newAccount;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Accounts Details based on a given mobileNumber
   */
  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = getCustomerOrThrow(mobileNumber);
    Accounts accounts =
        accountsRepository
            .findByCustomerId(customer.getCustomerId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Account", "customerId", customer.getCustomerId().toString()));
    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
    return customerDto;
  }

  private Customer getCustomerOrThrow(String mobileNumber) {
    return customerRepository
        .findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME_CUSTOMER, "mobileNumber",
            mobileNumber));
  }

  /**
   * @param customerDto - CustomerDto Object
   * @return boolean indicating if the update of Account details is successful or not
   */
  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    boolean isUpdated = false;
    AccountsDto accountsDto = customerDto.getAccountsDto();
    if (accountsDto != null) {
      Accounts accounts = getAccountOrThrow(accountsDto);
      AccountsMapper.mapToAccounts(accountsDto, accounts);
      accounts = accountsRepository.save(accounts);
      Long customerId = accounts.getCustomerId();
      Customer customer = getCustomerOrThrow(customerId);
      CustomerMapper.mapToCustomer(customerDto, customer);
      customerRepository.save(customer);
      isUpdated = true;
    }
    return isUpdated;
  }

  private Customer getCustomerOrThrow(Long customerId) {
    return customerRepository
        .findById(customerId)
        .orElseThrow(() ->
            new ResourceNotFoundException(
                RESOURCE_NAME_CUSTOMER, "CustomerID", customerId.toString()));
  }

  private Accounts getAccountOrThrow(AccountsDto accountsDto) {
    return accountsRepository
        .findById(accountsDto.getAccountNumber())
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Account", "AccountNumber", accountsDto.getAccountNumber().toString()));
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of Account details is successful or not
   */
  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = getCustomerOrThrow(mobileNumber);
    accountsRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());
    return true;
  }
}
