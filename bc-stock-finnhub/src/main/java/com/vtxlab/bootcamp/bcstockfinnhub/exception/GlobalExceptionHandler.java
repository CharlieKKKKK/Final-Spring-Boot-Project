package com.vtxlab.bootcamp.bcstockfinnhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;

@RestControllerAdvice // Bean: @ContollerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(FinnhubNotAvailableException.class)
  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
  public ApiResponse<Void> FinnhubNotAvailableExceptionHandler(
      FinnhubNotAvailableException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION.getCode()) //
        .message(Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> InvalidSymbolException(IllegalArgumentException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.INVALID_SYMBOL.getCode()) //
        .message(Syscode.INVALID_SYMBOL.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(RestClientException.class)
  @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
  public ApiResponse<Void> RestClientExceptionHandler(RestClientException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.JPH_NOT_AVAILABLE.getCode()) //
        .message(Syscode.JPH_NOT_AVAILABLE.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
  public ApiResponse<Void> npeExceptionHandler(NullPointerException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.NPE_EXCEPTION.getCode()) //
        .message(Syscode.NPE_EXCEPTION.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
  public ApiResponse<Void> npeExceptionHandler(Exception e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.GENERAL_EXCEPTION.getCode()) //
        .message(Syscode.GENERAL_EXCEPTION.getMessage()) //
        .data(null) //
        .build();
  }

}