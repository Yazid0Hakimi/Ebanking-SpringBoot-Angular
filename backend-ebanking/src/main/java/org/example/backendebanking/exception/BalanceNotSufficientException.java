package org.example.backendebanking.exception;

public class BalanceNotSufficientException extends Throwable {
    public BalanceNotSufficientException(String insufficientBalance) {
        super(insufficientBalance);
    }
}
