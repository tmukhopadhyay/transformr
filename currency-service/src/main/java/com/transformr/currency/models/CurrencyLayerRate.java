package com.transformr.currency.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyLayerRate {

	private boolean success;
	private String terms;
	private String privacy;
	private LocalDateTime timestamp;
	private String source;
	private Map<String, Float> quotes;

	public void setTimestamp(long timestamp) {
		this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
	}
}
