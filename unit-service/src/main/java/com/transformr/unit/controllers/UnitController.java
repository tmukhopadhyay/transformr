package com.transformr.unit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.transformr.unit.annotations.PublicRestController;
import com.transformr.unit.dto.MetricUnitDto;
import com.transformr.unit.services.UnitService;

import reactor.core.publisher.Mono;

@PublicRestController
public class UnitController {

	private final UnitService unitService;

	@Autowired
	public UnitController(UnitService unitService) {
		this.unitService = unitService;
	}

	@GetMapping(path = "/metrics")
	public Mono<MetricUnitDto> getMetricUnits() {
		return unitService.getMetricUnits();
	}
}
