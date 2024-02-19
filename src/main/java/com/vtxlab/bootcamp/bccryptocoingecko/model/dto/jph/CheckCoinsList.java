package com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckCoinsList {
  public String id;
  public String symbol;
  public String name;

  public static boolean isValidCoin(List<CheckCoinsList> coins, String coin) {
    for (CheckCoinsList c : coins) {
      if (c.getId().equals(coin)) {
        return true;
      }
    }
    return false;
  }
}
