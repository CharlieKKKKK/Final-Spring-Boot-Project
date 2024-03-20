package com.vtxlab.bootcamp.productdataservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vtxlab.bootcamp.productdataservice.service.ValidListService;

@Service
public class ValidListServiceImpl implements ValidListService{
  
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public boolean saveCoinList(){
    
  }
}
