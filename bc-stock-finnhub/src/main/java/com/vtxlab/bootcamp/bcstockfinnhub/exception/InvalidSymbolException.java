package com.vtxlab.bootcamp.bcstockfinnhub.exception;

import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;

public class InvalidSymbolException extends IllegalArgumentException{
  
    public InvalidSymbolException(Syscode syscode) {
    super(syscode.getMessage());
  }
}
