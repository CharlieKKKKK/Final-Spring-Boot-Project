package com.vtxlab.bootcamp.bcstockfinnhub.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.exception.FinnhubNotAvailableException;
import com.vtxlab.bootcamp.bcstockfinnhub.exception.InvalidSymbolException;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.FinnhubUrl;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.RedisHelper;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Scheme;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Profile;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Quote;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Symbol;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;

@Service
public class FinnhubJPH implements FinnhubService {

  @Value(value = "${api.jph.domain}")
  private String domain;

  @Value(value = "${api.jph.basePath}")
  private String basePath;

  @Value(value = "${api.jph.endpoints.profile2}")
  private String profile2Endpoint;

  @Value(value = "${api.jph.endpoints.quote}")
  private String quoteEndpoint;

  @Value(value = "${api.jph.apiKey}")
  private String apiKey;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public Quote getQuote(String symbol) {
    String url = FinnhubUrl.url(Scheme.HTTPS, domain, basePath, quoteEndpoint, symbol, apiKey);
    Quote quote = restTemplate.getForObject(url, Quote.class);
    if (symbol.equals("") || quote.getChange() == 0.0) {
      throw new InvalidSymbolException(Syscode.INVALID_SYMBOL);
    }
    return quote;
  }

  @Override
  public Profile getProfile2(String symbol) {
    String profileUrl = FinnhubUrl.url(Scheme.HTTPS, domain, basePath, profile2Endpoint, symbol, apiKey);
    Profile profile = restTemplate.getForObject(profileUrl, Profile.class);
    if (symbol.equals("") || profile.getIpo() == null) {
      throw new InvalidSymbolException(Syscode.INVALID_SYMBOL);
    }
    return profile;
  }

  @Autowired
  private RedisHelper redisHelper;

  @Override
  public Quote getRedisQuote(String symbol) throws JsonProcessingException {
    String QuoteName = "stock:finnhub:Quote:" + symbol.toUpperCase();
    Quote CheckData = redisHelper.get(QuoteName, Quote.class);
    if (CheckData == null) {
      String url = FinnhubUrl.url(Scheme.HTTPS, domain, basePath, quoteEndpoint, symbol, apiKey);
      Quote quote = restTemplate.getForObject(url, Quote.class);
      if (quote == null) {
        throw new FinnhubNotAvailableException(Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
      }
      System.out.println("Call Third Party API _ Quote");
      return quote;
    }
    return redisHelper.get(QuoteName, Quote.class);
  }

  @Override
  public Profile getRedisProfile2(String symbol) throws JsonProcessingException {
    String ProfileName = "stock:finnhub:Profile2:" + symbol.toUpperCase();
    Profile CheckData = redisHelper.get(ProfileName, Profile.class);
    if (CheckData == null) {
      String url = FinnhubUrl.url(Scheme.HTTPS, domain, basePath, profile2Endpoint, symbol, apiKey);
      Profile profile = restTemplate.getForObject(url, Profile.class);
      if (profile == null) {
        throw new FinnhubNotAvailableException(Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
      }
      System.out.println("Call Third Party API _ Profile2");
      return profile;
    }

    return redisHelper.get(ProfileName, Profile.class);
  }

  @Override
  public void saveStockToRedis() throws JsonProcessingException {
    for (Symbol symbolEnum : Symbol.values()) {
      String symbol = symbolEnum.name();
      String QuoteKey = "stock:finnhub:Quote:" + symbolEnum;
      Quote QuoteObject = this.getQuote(symbol);
      redisHelper.set(QuoteKey, QuoteObject);

      String Profile2Key = "stock:finnhub:Profile2:" + symbolEnum;
      Profile Profile2Object = this.getProfile2(symbol);
      redisHelper.set(Profile2Key, Profile2Object);

    }
  }

}
