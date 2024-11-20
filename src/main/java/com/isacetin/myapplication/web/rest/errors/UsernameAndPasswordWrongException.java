package com.isacetin.myapplication.web.rest.errors;

public class UsernameAndPasswordWrongException extends RuntimeException {
  public UsernameAndPasswordWrongException(String message) {
    super(message);
  }
}
