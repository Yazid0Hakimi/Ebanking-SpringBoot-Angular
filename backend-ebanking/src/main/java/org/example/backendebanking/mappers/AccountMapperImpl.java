package org.example.backendebanking.mappers;

import org.example.backendebanking.dtos.CurrentAccountDTO;
import org.example.backendebanking.dtos.CustomerDTO;
import org.example.backendebanking.dtos.OperationDTO;
import org.example.backendebanking.dtos.SavingAccountDTO;
import org.example.backendebanking.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountMapperImpl {
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        return customerDTO;
    }

    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CurrentAccountDTO currentAccountToCurrentAccountDto(CurrentAccount currentAccount) {
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        currentAccountDTO.setCustomerDTO(customerToCustomerDTO(currentAccount.getCustomer()));
        currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentAccountDTO;
    }

    public SavingAccountDTO savingAccountToSavingAccountDto(SavingAccount savingAccount) {
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        savingAccountDTO.setCustomerDTO(customerToCustomerDTO(savingAccount.getCustomer()));
        savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDTO;
    }

    public CurrentAccount currentAccountDTOToCurrentAccount(CurrentAccountDTO currentAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        currentAccount.setCustomer(customerDTOToCustomer(currentAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public SavingAccount savingAccountDTOToSavingAccount(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        savingAccount.setCustomer(customerDTOToCustomer(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public OperationDTO operationToOperationDTO (Operation operation) {
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation, operationDTO);
        return operationDTO;
    }

    public Operation operationDTOToOperation(OperationDTO operationDTO) {
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDTO, operation);
        return operation;
    }


}
