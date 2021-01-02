package com.transformr.unit.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetricUnitDto {

	private List<UnitDto> lengths;
	private List<UnitDto> areas;
	private List<UnitDto> volumes;
	private List<UnitDto> masses;
	private List<UnitDto> temperatures;

	public MetricUnitDto() {
		lengths = new ArrayList<>();
		areas = new ArrayList<>();
		volumes = new ArrayList<>();
		masses = new ArrayList<>();
		temperatures = new ArrayList<>();
	}
}
