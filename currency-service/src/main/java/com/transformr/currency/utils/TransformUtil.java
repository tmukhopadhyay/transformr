package com.transformr.currency.utils;

import java.time.LocalDateTime;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.transformr.currency.dto.CurrencyDto;
import com.transformr.currency.dto.ExchangeRateDto;
import com.transformr.currency.exceptions.CurrencyServiceException;
import com.transformr.currency.models.CurrencyLayerRate;

public class TransformUtil {

	private TransformUtil() {}

	public static ExchangeRateDto transformToExchangeRateDto(CurrencyLayerRate exchangeRate) {
		String source = exchangeRate.getSource();
		Entry<String, Float> quote = exchangeRate.getQuotes().entrySet().stream().findFirst().orElseThrow(CurrencyServiceException::new);

		return new ExchangeRateDto(
			new CurrencyDto(source, 1),
			new CurrencyDto(StringUtils.substringAfter(quote.getKey(), source), quote.getValue()),
			LocalDateTime.now()
		);
	}
}
