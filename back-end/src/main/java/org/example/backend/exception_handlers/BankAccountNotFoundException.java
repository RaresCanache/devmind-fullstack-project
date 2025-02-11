package org.example.backend.exception_handlers;

public class BankAccountNotFoundException extends RuntimeException {

  public BankAccountNotFoundException(String message) {
    super(message);
  }
}
