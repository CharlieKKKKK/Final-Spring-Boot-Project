package com.vtxlab.bootcamp.bccryptocoingecko;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.vtxlab.bootcamp.bccryptocoingecko.infra.CryptoUrl;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Scheme;
import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;

//@WebMvcTest(CoingeckoService.class)
@SpringBootTest
public class CryptoServiceImplTest {

  @Autowired
  private CoingeckoService coingeckoService;

  @MockBean
  private RestTemplate restTemplate;

  @Test
  void testGetAllCoins() {
    // List<Coin> testCurrency = coingeckoService.getAllCoins(null)
    // String coinsListUrl = CryptoUrl.coinsListUrl(Scheme.HTTPS, domain,
    // coinsListEndpoint, apiKey);

  }

}
