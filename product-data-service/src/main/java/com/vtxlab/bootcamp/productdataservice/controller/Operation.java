package com.vtxlab.bootcamp.productdataservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface Operation {
  
  @PostMapping(value = "/stock/finnhub")
  @ResponseStatus(value = HttpStatus.OK)
  public
}
