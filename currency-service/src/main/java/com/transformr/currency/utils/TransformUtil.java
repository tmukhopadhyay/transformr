package com.transformr.currency.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.transformr.currency.dto.CurrencyDto;
import com.transformr.currency.dto.CurrencyRateDto;
import com.transformr.currency.dto.ExchangeRateDto;
import com.transformr.currency.exceptions.CurrencyServiceException;
import com.transformr.currency.models.CurrencyLayerList;
import com.transformr.currency.models.CurrencyLayerRate;

public class TransformUtil {

	private TransformUtil() {}

	public static ExchangeRateDto transformToExchangeRateDto(CurrencyLayerRate exchangeRate) {
		String source = exchangeRate.getSource();
		Entry<String, Float> quote = exchangeRate.getQuotes().entrySet().stream().findFirst().orElseThrow(CurrencyServiceException::new);

		return new ExchangeRateDto(
			new CurrencyRateDto(source, 1),
			new CurrencyRateDto(StringUtils.substringAfter(quote.getKey(), source), quote.getValue()),
			LocalDateTime.now()
		);
	}

	public static List<CurrencyDto> transformToCurrencyDto(CurrencyLayerList currencyList) {
		return currencyList
				.getCurrencies()
				.entrySet()
				.stream()
				.map(c -> new CurrencyDto(c.getKey(), c.getValue()))
				.sorted((a, b) -> a.getAcronym().compareTo(b.getAcronym()))
				.collect(Collectors.toList());
	}
}
