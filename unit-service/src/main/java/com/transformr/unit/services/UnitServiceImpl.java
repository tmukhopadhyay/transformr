package com.transformr.unit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transformr.unit.dto.MetricUnitDto;
import com.transformr.unit.dto.UnitDto;
import com.transformr.unit.models.Unit;
import com.transformr.unit.repositories.UnitRepository;

import reactor.core.publisher.Mono;

@Service
public class UnitServiceImpl implements UnitService {

	private final UnitRepository unitRepository;

	@Autowired
	public UnitServiceImpl(UnitRepository unitRepository) {
		this.unitRepository = unitRepository;
	}

	@Override
	public Mono<MetricUnitDto> getMetricUnits() {
		MetricUnitDto metricUnitDto = new MetricUnitDto();
		unitRepository
			.findAll()
			.stream()
			.sorted((a, b) -> a.getName().compareTo(b.getName()))
			.forEach(unit -> addMetricUnit(metricUnitDto, unit));

		return Mono.just(metricUnitDto);
	}

	private void addMetricUnit(MetricUnitDto metricUnitDto, Unit unit) {
		List<UnitDto> units = null;

		switch(unit.getType()) {
			case AREA:
				units = metricUnitDto.getAreas();
				break;
			case LENGTH:
				units = metricUnitDto.getLengths();
				break;
			case MASS:
				units = metricUnitDto.getMasses();
				break;
			case VOLUME:
				units = metricUnitDto.getVolumes();
				break;
			case TEMPERATURE:
				units = metricUnitDto.getTemperatures();
				break;
			default:
				units = new ArrayList<>();
				break;
		}

		units.add(
			new UnitDto(
				unit.getId().toString(),
				unit.getName(),
				unit.getUnit(),
				unit.getValue(),
				unit.isBase(),
				unit.getFromFormula(),
				unit.getToFormula()
			)
		);
	}
}
