package com.vtxlab.bootcamp.bccryptocoingecko.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Currency;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;

@Component
@EnableScheduling
public class ScheduledConfig {

  @Autowired
  private CoingeckoService coingeckoService;

  @Scheduled(fixedRate = 60000)
  public void fixedRate() {
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
