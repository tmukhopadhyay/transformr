package com.transformr.time.services;

import org.springframework.stereotype.Service;

import com.transformr.time.dto.TimeZoneDto;

import reactor.core.publisher.Flux;

@Service
public interface TimeZoneService {

	public Flux<TimeZoneDto> getTimeZones();
}
