package com.vtxlab.bootcamp.bccryptocoingecko.exception;

import org.springframework.web.client.RestClientException;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;


public class CoingeckoNotAvailableException extends RestClientException{

  public CoingeckoNotAvailableException(Syscode syscode) {
    super(syscode.getMessage());
  }
  
}
