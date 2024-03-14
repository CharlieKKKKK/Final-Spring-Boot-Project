package com.vtxlab.bootcamp.bccryptocoingecko.controller.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtxlab.bootcamp.bccryptocoingecko.controller.Operation;
import com.vtxlab.bootcamp.bccryptocoingecko.exception.InvalidIdsInputException;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Currency;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;
import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.CheckCoinsList;
import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;

@RestController
@RequestMapping(value = "/crypto/coingecko/api/v1")
public class Controller implements Operation {

  @Autowired
  private CoingeckoService coingeckoService;

  @Override
  public ApiResponse<List<Coin>> getAllCoins(String currency) {
    // String 變enum先
    Currency currencyEnum = Currency.toCurrency(currency);
    List<Coin> getMarket = coingeckoService.getAllCoins(currencyEnum);
    return ApiResponse.<List<Coin>>builder()//
        .code(Syscode.OK.getCode())//
        .message(Syscode.OK.getMessage())//
        .data(getMarket)//
        .build();
  }

  @Override
  public ApiResponse<List<Coin>> getCoins(String currency, String ids) {
    String[] splitAllCoins = ids.split(",");
    Currency currencyEnum = Currency.toCurrency(currency);
    if (ids.isEmpty()) {
      return ApiResponse.<List<Coin>>builder()//
          .code(Syscode.OK.getCode())//
          .message(Syscode.OK.getMessage())//
          .data(coingeckoService.getAllCoins(currencyEnum))//
          .build();
    } else {
      List<Coin> Coins = new ArrayList<>();
      List<CheckCoinsList> getMarket = coingeckoService.getCoinsList();
      for (String coinId : splitAllCoins) {
        if (!(CheckCoinsList.isValidCoin(getMarket, coinId))){
          throw new InvalidIdsInputException();
        }
        Coins.addAll(coingeckoService.getCoins(currencyEnum, coinId));
      }
        return ApiResponse.<List<Coin>>builder()//
            .code(Syscode.OK.getCode())//
            .message(Syscode.OK.getMessage())//
            .data(Coins)//
            .build();
    }
  }
}
