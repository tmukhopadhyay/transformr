package com.transformr.unit.services;

import org.springframework.stereotype.Service;

import com.transformr.unit.dto.MetricUnitDto;

import reactor.core.publisher.Mono;

@Service
public interface UnitService {

	public Mono<MetricUnitDto> getMetricUnits();
}
