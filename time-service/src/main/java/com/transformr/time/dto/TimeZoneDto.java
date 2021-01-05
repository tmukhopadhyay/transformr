package com.transformr.time.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeZoneDto {

	private String name;
	private String abbreviation;
	private float offset;
	private String value;
}
