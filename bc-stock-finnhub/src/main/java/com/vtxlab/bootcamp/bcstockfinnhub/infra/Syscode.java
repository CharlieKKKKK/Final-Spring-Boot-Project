package com.vtxlab.bootcamp.bcstockfinnhub.infra;

import lombok.Getter;

@Getter
public enum Syscode {
  OK("000000", "OK."), //
  NOTFOUND("000001", "Not Found."), //
  JPH_NOT_AVAILABLE("000002", "JsonPlaceHolder API is unavailable."), //
  INVALID_SYMBOL("000003", "Invalid Symbol"),


  // Runtime Exception
  NPE_EXCEPTION("900000", "Runtime Exception - NPE."), //
  REST_CLIENT_EXCEPTION("900001", "RestClient Exception."), //
  FINNHUB_NOT_AVAILABLE_EXCEPTION("900002", "RestClientException - Finnhub Service is unavailable"), //
  GENERAL_EXCEPTION("999999", "General Exception.");

  private String code;

  private String message;

  private Syscode(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
