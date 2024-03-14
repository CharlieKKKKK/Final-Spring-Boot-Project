package com.vtxlab.bootcamp.bccryptocoingecko.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Currency;
import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.CheckCoinsList;
import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.Coin;

public interface CoingeckoService {

  List<Coin> getAllCoins(Currency currency);

  List<CheckCoinsList> getCoinsList();

  List<Coin> getCoins(Currency currency, String ids);

  void saveCoinToRedis(Currency currency, String ids) throws JsonProcessingException;
}
