package com.transformr.currency.services;

import org.springframework.stereotype.Service;

import com.transformr.currency.dto.ExchangeRateDto;

import reactor.core.publisher.Mono;

@Service
public interface ExchangeRateService {

	public Mono<ExchangeRateDto> getExchangeRate(String from, String to);
}
