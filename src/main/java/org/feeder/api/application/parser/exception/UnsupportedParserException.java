package org.feeder.api.application.parser.exception;

public class UnsupportedParserException extends RuntimeException {

  public UnsupportedParserException() {
  }

  public UnsupportedParserException(String message) {
    super(message);
  }

  public UnsupportedParserException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnsupportedParserException(Throwable cause) {
    super(cause);
  }

  public UnsupportedParserException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
