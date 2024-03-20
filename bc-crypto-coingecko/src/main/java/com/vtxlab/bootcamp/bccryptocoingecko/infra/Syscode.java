package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import lombok.Getter;

@Getter
public enum Syscode {
  OK("000000", "OK."), //
  NOTFOUND("000001", "Not Found."), //
  JPH_NOT_AVAILABLE("000002", "JsonPlaceHolder API is unavailable."), //
  INVALID_CURRENCY_INPUT("000003", "Currency Field: Invalid Input "), //
  INVALID_IDS_INPUT("000003", "Ids Field: Invalid Input "),

  // Runtime Exception
  REST_CLIENT_EXCEPTION("900001", "RestClient Exception."), //
  NPE_EXCEPTION("900000", "Runtime Exception - NPE."), //
  COINGECKO_NOT_AVAILABLE_EXCEPTION("900002", "RestClientException - Coingecko Service is unavailable"), //
  GENERAL_EXCEPTION("999999", "General Exception.");

  private String code;

  private String message;

  private Syscode(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
