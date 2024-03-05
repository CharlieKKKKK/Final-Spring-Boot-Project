package com.vtxlab.bootcamp.bcstockfinnhub.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;

@Component
@EnableScheduling
public class ScheduledConfig {

  @Autowired
  private FinnhubService finnhubService;

  @Scheduled(fixedRate = 60000)
  public void fixedRate() {

    try {
      finnhubService.saveStockToRedis();
    } catch (JsonProcessingException e) {

    }
  }
}
