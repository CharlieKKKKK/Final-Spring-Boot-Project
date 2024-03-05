package com.vtxlab.bootcamp.bcstockfinnhub.infra;

import org.springframework.web.util.UriComponentsBuilder;

public class FinnhubUrl {

  public static String url(Scheme scheme, String domain, String basePath, String endpoint, String symbol, String apiKey){
    return UriComponentsBuilder.newInstance()//
    .scheme(scheme.lowercaseName())//
    .host(domain)//
    .path(basePath)//
    .path(endpoint)//
    .queryParam("symbol",symbol.toUpperCase())//
    .queryParam("token", apiKey)//
    .toUriString();
  }

}
