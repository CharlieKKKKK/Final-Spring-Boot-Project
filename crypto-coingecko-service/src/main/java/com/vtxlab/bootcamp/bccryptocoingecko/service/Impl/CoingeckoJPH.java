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
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Scheme;
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

}
