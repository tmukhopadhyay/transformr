package com.transformr.currency.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyLayerList {

	private boolean success;
	private String terms;
	private String privacy;
	private Map<String, String> currencies;
}
