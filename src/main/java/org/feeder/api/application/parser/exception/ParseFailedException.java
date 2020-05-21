package org.feeder.api.application.parser.exception;

public class ParseFailedException extends RuntimeException {

  public ParseFailedException() {
  }

  public ParseFailedException(String message) {
    super(message);
  }

  public ParseFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParseFailedException(Throwable cause) {
    super(cause);
  }

  public ParseFailedException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
