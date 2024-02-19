package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {

  private String code;

  private String message;

  private T data;

}
