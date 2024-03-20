package com.vtxlab.bootcamp.bccryptocoingecko.service.Impl;

import java.util.ArrayList;
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
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.exception.CoingeckoNotAvailableException;
import com.vtxlab.bootcamp.bccryptocoingecko.exception.InvalidIdsInputException;
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
    HashSet<String> tempCoins = new HashSet<>();
    tempCoins.addAll(Arrays.asList(ids));
    List<Coin> marketDTO = this.getAllCoins(currency).stream()//
        .filter(e -> tempCoins.contains(e.getId()))//
        .collect(Collectors.toList());
    return marketDTO;
  }

  @Override
  public void saveCoinToRedis(Currency currency, String ids) throws JsonProcessingException {
    String coinKey = "crytpo:coingecko:coins-markets:" + currency + ":" + ids;
    System.out.println(coinKey);
    List<Coin> ListOfCoinObject = this.getCoins(currency, ids);
    redisHelper.set(coinKey, ListOfCoinObject);
  }

  @Override
  public List<Coin> getCoinToRedis(Currency currency, String ids) throws JsonProcessingException {
    String coinKey = "crytpo:coingecko:coins-markets:" + currency + ":" + ids;
    List<Coin> Coins = new ArrayList<>();
    Coin[] checkData = redisHelper.get(coinKey, Coin[].class);
    // Coins.addAll(this.getCoins(currency, coinId));
    if (checkData == null || checkData.length == 0) {
      Coins = this.getCoins(currency, ids);
      if (Coins == null) {
        throw new CoingeckoNotAvailableException(Syscode.COINGECKO_NOT_AVAILABLE_EXCEPTION);
      }
      System.out.println("Call Third Party API _ coingecko");
      return Coins;
    }
    // List<Coin> coins = Arrays.stream(checkData).collect(Collectors.toList());
    Coins = Arrays.asList(checkData);
    return Coins;
  }

}