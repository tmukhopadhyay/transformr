package com.transformr.currency.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDto {

	private CurrencyDto from;
	private CurrencyDto to;
	private LocalDateTime date;
}
