package org.feeder.api.application.parser.exception;

public class ParserTypeNotFoundException extends RuntimeException {

  public ParserTypeNotFoundException() {
    super();
  }

  public ParserTypeNotFoundException(String message) {
    super(message);
  }

  public ParserTypeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParserTypeNotFoundException(Throwable cause) {
    super(cause);
  }

  protected ParserTypeNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
