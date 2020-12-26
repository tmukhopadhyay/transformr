package com.transformr.currency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.transformr.currency.config.ExchangeApiConfig;
import com.transformr.currency.dto.ExchangeRateDto;
import com.transformr.currency.models.CurrencyLayerRate;
import com.transformr.currency.utils.TransformUtil;

import reactor.core.publisher.Mono;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
	
	private final WebClient webClient = WebClient.create();
	private final ExchangeApiConfig exchangeApiConfig;

	@Autowired
	ExchangeRateServiceImpl(ExchangeApiConfig exchangeApiConfig) {
		this.exchangeApiConfig = exchangeApiConfig;
	}

	public Mono<ExchangeRateDto> getExchangeRate(String from, String to) {
		String url = exchangeApiConfig.getBaseUrl() + "?access_key=" + exchangeApiConfig.getAccessKey() + "&source=" + from + "&currencies=" + to;
		return webClient
				.get()
				.uri(url)
				.retrieve()
				.bodyToMono(CurrencyLayerRate.class)
				.doOnError(ex -> ex.printStackTrace())
				.map(TransformUtil::transformToExchangeRateDto);
	}
}
