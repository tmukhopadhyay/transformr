package com.transformr.time.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeZoneDto {

	public String name;
	public float offset;
	public String value;
}
