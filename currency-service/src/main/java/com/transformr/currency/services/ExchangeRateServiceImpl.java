package com.transformr.currency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.transformr.currency.config.ExchangeApiConfig;
import com.transformr.currency.dto.CurrencyDto;
import com.transformr.currency.dto.ExchangeRateDto;
import com.transformr.currency.models.CurrencyLayerList;
import com.transformr.currency.models.CurrencyLayerRate;
import com.transformr.currency.utils.TransformUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	private final WebClient webClient = WebClient.create();
	private final ExchangeApiConfig exchangeApiConfig;

	@Autowired
	ExchangeRateServiceImpl(ExchangeApiConfig exchangeApiConfig) {
		this.exchangeApiConfig = exchangeApiConfig;
	}

	@Override
	public Flux<CurrencyDto> getCurrencies() {
		return webClient
				.get()
				.uri(getCurrencyListUrl())
				.retrieve()
				.bodyToMono(CurrencyLayerList.class)
				.doOnError(ex -> ex.printStackTrace())
				.flatMapMany(c -> Flux.fromIterable(TransformUtil.transformToCurrencyDto(c)));
	}

	@Override
	public Mono<ExchangeRateDto> getExchangeRate(String from, String to) {
		return webClient
				.get()
				.uri(getLiveRateUrl(from, to))
				.retrieve()
				.bodyToMono(CurrencyLayerRate.class)
				.doOnError(ex -> ex.printStackTrace())
				.map(TransformUtil::transformToExchangeRateDto);
	}

	private String getLiveRateUrl(String from, String to) {
		return exchangeApiConfig.getBaseUrl() +
				exchangeApiConfig.getListUrl() +
				"?access_key=" +
				exchangeApiConfig.getAccessKey() +
				"&source=" +
				from +
				"&currencies=" +
				to;
	}

	private String getCurrencyListUrl() {
		return exchangeApiConfig.getBaseUrl() +
				exchangeApiConfig.getListUrl() +
				"?access_key=" +
				exchangeApiConfig.getAccessKey();
	}
}
