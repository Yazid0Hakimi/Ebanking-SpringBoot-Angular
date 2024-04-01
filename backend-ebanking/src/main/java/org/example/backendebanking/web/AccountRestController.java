package org.example.backendebanking.web;

import lombok.AllArgsConstructor;
import org.example.backendebanking.dtos.*;
import org.example.backendebanking.entities.Account;
import org.example.backendebanking.exception.AccountNotFoundException;
import org.example.backendebanking.exception.BalanceNotSufficientException;
import org.example.backendebanking.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class AccountRestController {
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccount() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable String id) throws AccountNotFoundException {
        return accountService.getAccount(id);
    }

    @GetMapping("/accounts/{Id}/operations")
    public List<OperationDTO> getHistory(@PathVariable String Id) {
        return accountService.accountHistory(Id);
    }

    @GetMapping("/accounts/{Id}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String Id,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "5") int size) throws AccountNotFoundException {
        return accountService.accountPagesHistory(Id, page, size);
    }

    @PostMapping("/account/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException {
        accountService.debit(debitDTO.getAccount(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/account/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws AccountNotFoundException, BalanceNotSufficientException {
        accountService.credit(creditDTO.getAccount(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;

    }
}
