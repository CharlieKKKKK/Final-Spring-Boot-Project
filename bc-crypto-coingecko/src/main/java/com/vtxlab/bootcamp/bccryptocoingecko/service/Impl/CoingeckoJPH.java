package com.vtxlab.bootcamp.bccryptocoingecko.service.Impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vtxlab.bootcamp.bccryptocoingecko.infra.Currency;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.RedisHelper;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Scheme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.CryptoUrl;
import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.CheckCoinsList;
import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;

@Service
public class CoingeckoJPH implements CoingeckoService {

  @Value(value = "${api.jph.domain}")
  private String domain;

  @Value(value = "${api.jph.endpoints.market}")
  private String coinEndpoint;

  @Value(value = "${api.jph.endpoints.list}")
  private String coinsListEndpoint;

  @Value(value = "${api.jph.path.apiKey}")
  private String apiKey;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisHelper redisHelper;

  @Override
  public List<Coin> getAllCoins(Currency currency) {
    String coinUrl = CryptoUrl//
        .url(Scheme.HTTPS, domain, coinEndpoint, currency, apiKey);
    Coin[] coin = restTemplate.getForObject(coinUrl, Coin[].class);

    return Arrays.stream(coin)//
        .collect(Collectors.toList());
  }

  @Override
  public List<CheckCoinsList> getCoinsList() {
    String coinsListUrl = CryptoUrl.coinsListUrl(Scheme.HTTPS, domain, coinsListEndpoint, apiKey);
    CheckCoinsList[] coin = restTemplate.getForObject(coinsListUrl, CheckCoinsList[].class);

    return Arrays.stream(coin)//
        .collect(Collectors.toList());
  }

  @Override
  public List<Coin> getCoins(Currency currency, String ids) {
    // System.out.println("geeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeetCoins");
    HashSet<String> tempCoins = new HashSet<>();
    tempCoins.addAll(Arrays.asList(ids));
    List<Coin> marketDTO = this.getAllCoins(currency).stream()//
        .filter(e -> tempCoins.contains(e.getId()))//
        .collect(Collectors.toList());
    // System.out.println("getCoinnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnns");
    return marketDTO;
  }

  @Override
  public void saveCoinToRedis(Currency currency, String ids) throws JsonProcessingException {
    String coinKey = "crytpo:coingecko:coins-markets:" + currency + ":" + ids;
    System.out.println(coinKey);
    List<Coin> ListOfCoinObject = this.getCoins(currency, ids);
    System.out.println(ListOfCoinObject);
    // System.out.println("saveCoooooooooooooooooooooooooooooin");
    redisHelper.set(coinKey, ListOfCoinObject);
    System.out.println("saveCoin OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
  }

}