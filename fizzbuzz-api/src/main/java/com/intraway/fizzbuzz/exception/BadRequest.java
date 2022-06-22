package com.intraway.fizzbuzz.exception;

import lombok.Data;

import java.util.Date;

@Data
public class BadRequest {

  private Date timestamp;
  private String status;
  private String message;
  private String path;

  public BadRequest(final Date timestamp, final String status, final String message, final String path) {
    this.timestamp = timestamp;
    this.status = status;
    this.message = message;
    this.path = path;
  }

  public BadRequest(){}
}
