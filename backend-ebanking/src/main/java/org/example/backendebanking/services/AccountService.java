package org.example.backendebanking.services;

import org.example.backendebanking.dtos.*;
import org.example.backendebanking.exception.AccountNotFoundException;
import org.example.backendebanking.exception.BalanceNotSufficientException;
import org.example.backendebanking.exception.CustomerNotFoundException;

import java.util.List;

public interface AccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
    CurrentAccountDTO saveCurrentAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;

    SavingAccountDTO saveSavingAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomer(Long costumerId) throws CustomerNotFoundException;

    AccountDTO getAccount(String accountId) throws AccountNotFoundException;

    void debit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BalanceNotSufficientException, AccountNotFoundException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException;

    List<AccountDTO> getAccounts();

    List<OperationDTO> accountHistory(String accoundId);

    AccountHistoryDTO accountPagesHistory(String accoundId, int page, int size) throws AccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
