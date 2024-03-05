package com.vtxlab.bootcamp.bcstockfinnhub.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;

@Component
public class AppRunner implements CommandLineRunner {

  @Autowired
  private FinnhubService finnhubService;

  @Override
  public void run(String... args) throws Exception {

    try {
      finnhubService.saveStockToRedis();
    } catch (JsonProcessingException e) {

    }
  }

}
