package com.vtxlab.bootcamp.bcstockfinnhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Profile;
import com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph.Quote;

public interface Operation {

  @GetMapping(value = "/quoteOkTest")
  ApiResponse<Quote> getQuote(@RequestParam(value = "symbol", required = true) String symbol);

  @GetMapping(value = "/profile2OkTest")
  ApiResponse<Profile> getProfile2(@RequestParam(value = "symbol", required = true) String symbol);

  @GetMapping(value = "/quote")
  ApiResponse<Quote> getRedisQuote(@RequestParam(value = "symbol", required = true) String symbol)//
      throws JsonProcessingException;

  @GetMapping(value = "/profile2")
  ApiResponse<Profile> getRedisProfile2(@RequestParam(value = "symbol", required = true) String symbol)//
      throws JsonProcessingException;
}
// step 1 : nedd to use Redis
// step2 : add Redis dependency
// step 3 :AppConfig -< create @Bean
// step 4 : encapsulate RedisTemplate -> RedisHelper(dthe nam define yourself)
// step 5: set data into Redis + get data from Redis
// step 6 : @Autowired RedisHelper -> redishelper.set(xxx) ,
// redishelper.get(xxx)