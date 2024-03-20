package com.vtxlab.bootcamp.bcstockfinnhub.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.controller.Operation;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Profile;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Quote;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;

@RestController
@RequestMapping(value = "/stock/finnhub.io/api/v1")
public class Controller implements Operation {

  @Autowired
  private FinnhubService FinnhubService;

  @Override
  public ApiResponse<Quote> getQuote(String symbol) {
    Quote quote = FinnhubService.getQuote(symbol);
    return ApiResponse.<Quote>builder()
        .code(Syscode.OK.getCode()) //
        .message(Syscode.OK.getMessage()) //
        .data(quote) //
        .build();

  }

  @Override
  public ApiResponse<Profile> getProfile2(String symbol) {
    Profile profile = FinnhubService.getProfile2(symbol);
    return ApiResponse.<Profile>builder()
        .code(Syscode.OK.getCode()) //
        .message(Syscode.OK.getMessage()) //
        .data(profile) //
        .build();
  }

  @Override
  public ApiResponse<Quote> getRedisQuote(String symbol) throws JsonProcessingException {
    Quote quote = FinnhubService.getRedisQuote(symbol);
    return ApiResponse.<Quote>builder()
        .code(Syscode.OK.getCode()) //
        .message(Syscode.OK.getMessage()) //
        .data(quote) //
        .build();
  }

  @Override
  public ApiResponse<Profile> getRedisProfile2(String symbol) throws JsonProcessingException {
    Profile profile = FinnhubService.getRedisProfile2(symbol);
    return ApiResponse.<Profile>builder()
        .code(Syscode.OK.getCode()) //
        .message(Syscode.OK.getMessage()) //
        .data(profile) //
        .build();
  }
}