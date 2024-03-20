package com.vtxlab.bootcamp.bccryptocoingecko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;

@RestControllerAdvice // Bean: @ContollerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(CoingeckoNotAvailableException.class)
  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
  public ApiResponse<Void> FinnhubNotAvailableExceptionHandler(
      CoingeckoNotAvailableException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.COINGECKO_NOT_AVAILABLE_EXCEPTION.getCode()) //
        .message(Syscode.COINGECKO_NOT_AVAILABLE_EXCEPTION.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(InvalidCurrencyInputException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> InvalidOperaExceptionHandler(InvalidCurrencyInputException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.INVALID_CURRENCY_INPUT.getCode()) //
        .message(Syscode.INVALID_CURRENCY_INPUT.getMessage()) //
        .build();
  }

  @ExceptionHandler(InvalidIdsInputException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> InvalidInputExceptionHandler(InvalidIdsInputException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.INVALID_IDS_INPUT.getCode()) //
        .message(Syscode.INVALID_IDS_INPUT.getMessage()) //
        .build();
  }

  @ExceptionHandler(RestClientException.class)
  @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
  public ApiResponse<Void> RestClientExceptionHandler(RestClientException e) {
    return ApiResponse.<Void>builder() //
        .code(Syscode.JPH_NOT_AVAILABLE.getCode()) //
        .message(Syscode.JPH_NOT_AVAILABLE.getMessage()) //
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