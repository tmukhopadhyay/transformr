package com.transformr.unit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDto {

	private String id;
	private String name;
	private String unit;
	private double value;
	private boolean base;
	private String fromFormula;
	private String toFormula;
}
