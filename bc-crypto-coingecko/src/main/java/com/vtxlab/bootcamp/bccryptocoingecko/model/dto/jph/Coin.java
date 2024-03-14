package com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Coin {

  private String id;
  private String symbol;
  private String name;
  private String image;
  @JsonProperty(value = "current_price")
  private double currentPrice;
  @JsonProperty(value = "market_cap")
  private Long marketCap;
  @JsonProperty(value = "market_cap_rank")
  private int marketCapRank;
  @JsonProperty(value = "fully_diluted_valuation")
  private Long fullyDilutedValuation;
  @JsonProperty(value = "total_volume")
  private Long totalVolume;
  @JsonProperty(value = "high_24h")
  private double high24h;
  @JsonProperty(value = "low_24h")
  private double low24h;
  @JsonProperty(value = "price_change_24h")
  private double priceChange24h;
  @JsonProperty(value = "price_change_percentage_24h")
  private double priceChangePercentage24h;
  @JsonProperty(value = "market_cap_change_24h")
  private double marketCapChange24h;
  @JsonProperty(value = "market_cap_change_percentage_24h")
  private double marketCapChangePercentage24h;
  @JsonProperty(value = "circulating_supply")
  private double circulatingSupply;
  @JsonProperty(value = "total_supply")
  private double totalSupply;
  @JsonProperty(value = "max_supply")
  private double maxSupply;
  private double ath;
  @JsonProperty(value = "ath_change_percentage")
  private double athChangePercentage;
  @JsonProperty(value = "ath_date")
  private LocalDateTime athDate;
  private double atl;
  @JsonProperty(value = "atl_change_percentage")
  private double atlChangePercentage;
  @JsonProperty(value = "atl_date")
  private LocalDateTime atlDate;
  private Roi roi;
  @JsonProperty(value = "last_updated")
  private LocalDateTime lastUpdated;

  @AllArgsConstructor
  @Builder
  @Data
  @NoArgsConstructor
  public static class Roi {
    private double times;
    private String currency;
    private double percentage;
  }

}
