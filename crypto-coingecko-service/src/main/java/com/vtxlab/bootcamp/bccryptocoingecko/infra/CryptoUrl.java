package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import org.springframework.web.util.UriComponentsBuilder;

public class CryptoUrl {

  public static String url(Scheme scheme, String domain, String endpoint, Currency currency, String apiKey) {
    return UriComponentsBuilder.newInstance() //
        .scheme(scheme.lowercaseName()) // .scheme("https")
        .host(domain) //
        .pathSegment(endpoint.split("/"))//
        .queryParam("vs_currency",currency.name().toLowerCase())//
        .queryParam("x_cg_demo_api_key",apiKey)
        .toUriString(); // handle special character, such as : / \
  }

  public static String coinsListUrl(Scheme scheme, String domain, String endpoint, String apiKey){
    return UriComponentsBuilder.newInstance() //
        .scheme(scheme.lowercaseName()) // .scheme("https")
        .host(domain) //
        .pathSegment(endpoint.split("/"))//
        .queryParam("x_cg_demo_api_key",apiKey)
        .toUriString(); // handle special character, such as : / \
  }

}
