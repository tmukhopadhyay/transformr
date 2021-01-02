package com.transformr.unit.listeners;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transformr.unit.dto.MetricUnitDto;
import com.transformr.unit.enums.UnitType;
import com.transformr.unit.models.Unit;
import com.transformr.unit.repositories.UnitRepository;

@Component
public class ApplicationEventListener {

	private final ObjectMapper objectMapper;
	private final UnitRepository unitRepository;

	@Value("classpath:units.json")
	Resource resource;

	@Autowired
	ApplicationEventListener(ObjectMapper objectMapper, UnitRepository unitRepository) {
		this.objectMapper = objectMapper;
		this.unitRepository = unitRepository;
	}

	@EventListener
	public void onStartUp(ContextRefreshedEvent event) throws JsonParseException, IOException {
		MetricUnitDto metricUnitDto = objectMapper.readValue(resource.getInputStream(), MetricUnitDto.class);
		metricUnitDto.getAreas().forEach(u -> unitRepository.saveAndFlush(new Unit(u.getName(), u.getUnit(), UnitType.AREA, u.getValue())));
		metricUnitDto.getLengths().forEach(u -> unitRepository.saveAndFlush(new Unit(u.getName(), u.getUnit(), UnitType.LENGTH, u.getValue())));
		metricUnitDto.getMasses().forEach(u -> unitRepository.saveAndFlush(new Unit(u.getName(), u.getUnit(), UnitType.MASS, u.getValue())));
		metricUnitDto.getTemperatures().forEach(u -> unitRepository.saveAndFlush(new Unit(u.getName(), u.getUnit(), UnitType.TEMPERATURE, u.getValue())));
		metricUnitDto.getVolumes().forEach(u -> unitRepository.saveAndFlush(new Unit(u.getName(), u.getUnit(), UnitType.VOLUME, u.getValue())));
	}
}
