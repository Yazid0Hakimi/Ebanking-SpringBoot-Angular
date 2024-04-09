package org.example.backendebanking.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backendebanking.dtos.*;
import org.example.backendebanking.entities.*;
import org.example.backendebanking.enums.AccountStatus;
import org.example.backendebanking.enums.OperationType;
import org.example.backendebanking.exception.AccountNotFoundException;
import org.example.backendebanking.exception.BalanceNotSufficientException;
import org.example.backendebanking.exception.CustomerNotFoundException;
import org.example.backendebanking.mappers.AccountMapperImpl;
import org.example.backendebanking.repositories.AccountRepository;
import org.example.backendebanking.repositories.CustumerRepository;
import org.example.backendebanking.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private CustumerRepository customerRepository;
    private OperationRepository operationRepository;
    private AccountMapperImpl accountMapperImpl;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving customer: " + customerDTO);
        Customer customer = accountMapperImpl.customerDTOToCustomer(customerDTO);
        customerRepository.save(customer);
        return customerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = accountMapperImpl.customerDTOToCustomer(customerDTO);
        customerRepository.save(customer);
        return customerDTO;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CurrentAccountDTO saveCurrentAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount account = new CurrentAccount();
        account.setId(UUID.randomUUID().toString());
        account.setBalance(initialBalance);
        account.setCreatedAt(new Date());
        account.setStatus(AccountStatus.CREATED);
        account.setCustomer(customer);
        account.setOverDraft(overDraft);
        accountRepository.save(account);
        return accountMapperImpl.currentAccountToCurrentAccountDto(account);

    }

    @Override
    public SavingAccountDTO saveSavingAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        SavingAccount account = new SavingAccount();
        account.setId(UUID.randomUUID().toString());
        account.setBalance(initialBalance);
        account.setCreatedAt(new Date());
        account.setStatus(AccountStatus.CREATED);
        account.setCustomer(customer);
        account.setInterestRate(interestRate);
        accountRepository.save(account);

        return accountMapperImpl.savingAccountToSavingAccountDto(account);

    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> accountMapperImpl.customerToCustomerDTO(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long costumerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(costumerId).orElseThrow(() -> new CustomerNotFoundException("customer Not Found"));
        CustomerDTO customerDTO = accountMapperImpl.customerToCustomerDTO(customer);
        return customerDTO;
    }

    @Override
    public AccountDTO getAccount(String accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) account;
            return accountMapperImpl.savingAccountToSavingAccountDto(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) account;
            return accountMapperImpl.currentAccountToCurrentAccountDto(currentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.getBalance() < amount) throw new BalanceNotSufficientException("Insufficient balance");

        Operation operation = new Operation();

        operation.setAccount(account);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setType(OperationType.DEBIT);

        operationRepository.save(operation);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

    }

    @Override
    public void credit(String accountId, double amount, String description) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        Operation operation = new Operation();

        operation.setAccount(account);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setType(OperationType.CREDIT);

        operationRepository.save(operation);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "transfere to " + accountIdDestination);
        credit(accountIdDestination, amount, "transfere from " + accountIdSource);
    }

    @Override
    public List<AccountDTO> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = accounts.stream().map(account -> {
            if (account instanceof CurrentAccount) {
                CurrentAccount currentAccount = (CurrentAccount) account;
                return accountMapperImpl.currentAccountToCurrentAccountDto(currentAccount);
            } else {
                SavingAccount savingAccount = (SavingAccount) account;
                return accountMapperImpl.savingAccountToSavingAccountDto(savingAccount);
            }
        }).collect(Collectors.toList());

        return accountDTOS;
    }

    @Override
    public List<OperationDTO> accountHistory(String accoundId) {
        return operationRepository.findByAccount_Id(accoundId).stream().map(operation -> accountMapperImpl.operationToOperationDTO(operation)).collect(Collectors.toList());

    }

    @Override
    public AccountHistoryDTO accountPagesHistory(String accoundId, int page, int size) throws AccountNotFoundException {
        Account account = accountRepository.findById(accoundId).orElseThrow(() -> new AccountNotFoundException("Account not founds"));
        Page<Operation> operations = operationRepository.findByAccount_IdOrderByOperationDateDesc(accoundId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        accountHistoryDTO.setOperationDTOS(operations.getContent().stream().map(operation -> accountMapperImpl.operationToOperationDTO(operation)).collect(Collectors.toList()));
        accountHistoryDTO.setAccountId(account.getId());
        accountHistoryDTO.setBalance(account.getBalance());
        accountHistoryDTO.setTotalePages(operations.getTotalPages());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setCurrentPage(size);
        return accountHistoryDTO;
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.findByNameContains(keyword);
        List<CustomerDTO> customersDTO = customers.stream().map(customer -> accountMapperImpl.customerToCustomerDTO(customer)).collect(Collectors.toList());
        return customersDTO;
    }
}
