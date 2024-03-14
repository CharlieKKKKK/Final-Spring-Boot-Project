package com.vtxlab.bootcamp.bccryptocoingecko.infra;


public enum Scheme {
  HTTPS, HTTP,;

  public String lowercaseName() {
    return this.name().toLowerCase();
  }
  
}
