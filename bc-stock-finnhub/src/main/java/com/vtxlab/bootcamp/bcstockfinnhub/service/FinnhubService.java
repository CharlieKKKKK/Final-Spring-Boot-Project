package com.vtxlab.bootcamp.bcstockfinnhub.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Profile;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Quote;

@Service
public interface FinnhubService {

  Quote getQuote(String symbol);

  Profile getProfile2(String symbol);

  Quote getRedisQuote(String symbol) throws JsonProcessingException;

  Profile getRedisProfile2(String symbol) throws JsonProcessingException;

  void saveStockToRedis() throws JsonProcessingException;
}
