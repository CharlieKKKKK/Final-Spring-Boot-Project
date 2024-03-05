package com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Quote {

  @JsonProperty(value = "c")
  public double CurrentPrice;
  @JsonProperty(value = "d")
  public double Change;
  @JsonProperty(value = "dp")
  public double PercentChange;
  @JsonProperty(value = "h")
  public double HighPriceOfTheDay;
  @JsonProperty(value = "l")
  public double LowPriceOfTheDay;
  @JsonProperty(value = "o")
  public double OpenPriceOfTheDay;
  @JsonProperty(value = "pc")
  public double PreviousClosePrice;
  @JsonProperty(value = "t")
  public double t;

}
