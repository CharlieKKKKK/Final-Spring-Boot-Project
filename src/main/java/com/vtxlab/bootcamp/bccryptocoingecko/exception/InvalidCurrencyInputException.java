package com.vtxlab.bootcamp.bccryptocoingecko.exception;

import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;

public class InvalidCurrencyInputException extends IllegalArgumentException{
  
  public InvalidCurrencyInputException(){
    super(Syscode.INVALID_CURRENCY_INPUT.getMessage());
}
}
