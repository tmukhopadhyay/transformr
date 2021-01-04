package com.transformr.time.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.transformr.time.annotations.PublicRestController;
import com.transformr.time.dto.TimeZoneDto;
import com.transformr.time.services.TimeZoneService;

import reactor.core.publisher.Flux;

@PublicRestController
public class TimeZoneController {

	private final TimeZoneService timeZoneService;

	@Autowired
	TimeZoneController(TimeZoneService timeZoneService) {
		this.timeZoneService = timeZoneService;
	}

	@GetMapping(path = "/zones")
	public Flux<TimeZoneDto> getTimeZones() {
		return timeZoneService.getTimeZones();
	}
}
