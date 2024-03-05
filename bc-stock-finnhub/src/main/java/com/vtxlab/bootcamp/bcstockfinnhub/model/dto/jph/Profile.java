package com.vtxlab.bootcamp.bcstockfinnhub.model.dto.jph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Profile {

  public String country;
  public String currency;
  public String estimateCurrency;
  public String exchange;
  public String finnhubIndustry;
  public String ipo;
  public String logo;
  public double marketCapitalization;
  public String name;
  public String phone;
  public double shareOutstanding;
  public String ticker;
  public String weburl;
}
