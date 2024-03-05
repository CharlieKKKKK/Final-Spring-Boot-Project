package com.vtxlab.bootcamp.bcstockfinnhub.infra;


public enum Scheme {
  HTTPS, HTTP,;

  public String lowercaseName() {
    return this.name().toLowerCase();
  }
  
}
