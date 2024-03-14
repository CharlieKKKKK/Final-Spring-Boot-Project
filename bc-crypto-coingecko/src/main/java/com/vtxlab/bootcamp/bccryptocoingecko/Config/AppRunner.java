package com.vtxlab.bootcamp.bccryptocoingecko.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Currency;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;

@Component
public class AppRunner implements CommandLineRunner {

  @Autowired
  private CoingeckoService coingeckoService;

  @Override
  public void run(String... args) throws Exception {

    try {
      String ids = "ethereum";
      for (Currency crrencyEnum : Currency.values()) {
      coingeckoService.saveCoinToRedis(crrencyEnum, ids);
      }
    } catch (JsonProcessingException e) {

    }

    try {
      String ids = "bitcoin";
      for (Currency crrencyEnum : Currency.values()) {
        coingeckoService.saveCoinToRedis(crrencyEnum, ids);
      }
    } catch (JsonProcessingException e) {

    }
  }
}
