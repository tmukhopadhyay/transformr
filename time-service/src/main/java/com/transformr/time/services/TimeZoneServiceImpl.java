package com.transformr.time.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.transformr.time.dto.TimeZoneDto;

import reactor.core.publisher.Flux;

@Service
public class TimeZoneServiceImpl implements TimeZoneService {

	@Override
	public Flux<TimeZoneDto> getTimeZones() {
		List<TimeZoneDto> timeZones = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();

		ZoneId
			.getAvailableZoneIds()
			.stream()
			.map(ZoneId::of)
			.sorted(new ZoneComparator())
			.filter(zoneId -> shouldTimeZoneDisplay(zoneId))
			.forEach(zoneId -> {
				timeZones.add(new TimeZoneDto(
					zoneId.getId(),
					Float.parseFloat(getOffset(now, zoneId).replace(":", ".")),
					String.format("%s (UTC%s)", zoneId.getId(), getOffset(now, zoneId))
				));
			});

		return Flux.fromIterable(timeZones);
	}

	private String getOffset(LocalDateTime dateTime, ZoneId id) {
		return dateTime
				.atZone(id)
				.getOffset()
				.getId()
				.replace("Z", "+00:00");
	}

	private boolean shouldTimeZoneDisplay(ZoneId zoneId) {
		return zoneId.getId().indexOf("/") != -1 &&
				zoneId.getId().indexOf("Etc/") == -1 &&
				zoneId.getId().indexOf("SystemV/") == -1;
	}

	private class ZoneComparator implements Comparator<ZoneId> {

		@Override
		public int compare(ZoneId zoneId1, ZoneId zoneId2) {
			LocalDateTime now = LocalDateTime.now();
			ZoneOffset offset1 = now.atZone(zoneId1).getOffset();
			ZoneOffset offset2 = now.atZone(zoneId2).getOffset();

			int comparable = offset2.compareTo(offset1);
			if(comparable == 0) {
				return zoneId1.getId().compareTo(zoneId2.getId());
			}
			return comparable;
		}
	}
}
