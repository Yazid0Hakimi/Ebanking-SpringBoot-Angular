package org.example.backendebanking.exception;
public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String accountNotFound) {
        super(accountNotFound);
    }
}
