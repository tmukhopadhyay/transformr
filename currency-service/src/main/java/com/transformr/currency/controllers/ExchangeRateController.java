package com.transformr.currency.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transformr.currency.annotations.PublicRestController;
import com.transformr.currency.dto.ExchangeRateDto;
import com.transformr.currency.exceptions.CurrencyServiceException;
import com.transformr.currency.services.ExchangeRateService;

import reactor.core.publisher.Mono;

@PublicRestController
public class ExchangeRateController {

	private final ExchangeRateService exchangeRateService;

	@Autowired
	ExchangeRateController(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}

	@GetMapping(path = "/exchange", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<ExchangeRateDto> getExchangeRate(
		@RequestParam(required = true) String from,
		@RequestParam(required = true) String to
	) {
		if(StringUtils.isBlank(from) || StringUtils.isBlank(to)) {
			throw new CurrencyServiceException("Missing mandatory fields");
		}

		return exchangeRateService.getExchangeRate(from, to);
	}
}
