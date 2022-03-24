package com.oasis.admin.geography.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.admin.geography.data.TimezoneRepository;
import com.oasis.admin.geography.model.Timezone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class TimezoneController {
	
	@Autowired
	private TimezoneRepository tzRepo;
	
	@GetMapping("/timezones")
	public Iterable<Timezone> getAllTimezones(@RequestParam int limit, @RequestParam int offset) {
		
		log.debug("getAllTimezones: getting Timezones limit="+limit+", offset="+offset);
		
		return tzRepo.findAll(PageRequest.of(offset, limit+offset, Sort.by("timezoneId").ascending())).getContent();
	}
	
	
	@PostMapping("/timezone")
	public Timezone createTimezones(@RequestBody Timezone timezone) {
		
		log.debug("createTimezone:");
		//timezone.setCountry(null);
		
		return tzRepo.save(timezone);
	}
	
	@PostMapping("/timezones")
	public List<Timezone> createMultiTimezones(@RequestBody List<Timezone> timezones) {
		
		log.debug("createTimezone:");
		//timezone.setCountry(null);
		
		List<Timezone> savedTz = new ArrayList<>(timezones.size());
		
		for (Timezone tz: timezones) {
			savedTz.add(tzRepo.save(tz));
		}
		
		
		return savedTz;
	}

}
