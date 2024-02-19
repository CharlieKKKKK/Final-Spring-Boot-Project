package com.vtxlab.bootcamp.bccryptocoingecko.exception;

import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;

public class InvalidIdsInputException extends IllegalArgumentException{
  
  public InvalidIdsInputException(){
    super(Syscode.INVALID_IDS_INPUT.getMessage());
}
}
