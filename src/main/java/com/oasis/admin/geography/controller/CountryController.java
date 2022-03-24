package com.oasis.admin.geography.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.admin.geography.data.CountryRepository;
import com.oasis.admin.geography.model.Country;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class CountryController {

	@Autowired
	private CountryRepository countryRepo;
	
	@GetMapping("/countries")
	public ResponseEntity<List<Country>> getAllCountries(@RequestParam(required=false) String name) {
		
		List<Country> countries = new ArrayList<Country>();
	    if (name == null)
	    	countryRepo.findAll().forEach(countries::add);
	    else
	    	countryRepo.findByNameContaining(name).forEach(countries::add);
	    if (countries.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(countries, HttpStatus.OK);
		
		/*
		 * Optional<Country> countryinDB = countryRepo.findById(country.getCountryId());
		 * if (countryinDB.isEmpty()) { return countryRepo.save(country); }else { return
		 * countryinDB.get(); }
		 */
		
		
	}
	
	@PostMapping("/countries")
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {
		
		/*
		 * Optional<Country> countryinDB = countryRepo.findById(country.getCountryId());
		 * if (countryinDB.isEmpty()) { return countryRepo.save(country); }else { return
		 * countryinDB.get(); }
		 */
		
		Country _country = countryRepo.save(country);
		return new ResponseEntity<>(_country, HttpStatus.CREATED);
		
	}
}
