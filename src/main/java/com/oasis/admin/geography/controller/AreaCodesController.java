package com.oasis.admin.geography.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.admin.geography.data.AreaCodeRepository;
import com.oasis.admin.geography.data.CountryRepository;
import com.oasis.admin.geography.data.StateRepository;
import com.oasis.admin.geography.exceptions.CountryNotFoundException;
import com.oasis.admin.geography.model.AreaCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class AreaCodesController {
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private AreaCodeRepository areaCodeRepository;
	
	@Autowired
	private StateRepository stateRepo;
	
	@GetMapping("/countries/{countryId}/areacodes")
	public ResponseEntity<List<AreaCode>> getAllAreaCodes(@RequestParam int limit, @RequestParam int offset, @PathVariable Integer countryId) {
		
		log.debug("getAllAreaCodes: getting areacodes limit="+limit+", offset="+offset);
		
		if(!countryRepo.existsById(countryId)) {
			throw new CountryNotFoundException(countryId);
		}
		//List<AreaCode> areaCodes= areaCodeRepository.findByCountryId(countryId);
		
		
		
		//TODO: Paging 
		return new ResponseEntity<>(areaCodeRepository.findAll(PageRequest.of(offset, limit+offset, Sort.by("id").ascending())).getContent(), HttpStatus.OK);
		
		//return areaCodeRepository.findAll(PageRequest.of(offset, limit+offset, Sort.by("id").ascending())).getContent();
	}
	
	@PostMapping("/countries/{countryId}/areacodes")
	public ResponseEntity<AreaCode> createAreaCode(@PathVariable Integer countryId,@RequestBody AreaCode areaCode) {
		
		log.debug("createAreaCode: create areacode "+areaCode);
		
		AreaCode areacode = countryRepo.findById(countryId).map(country -> {
		      //areaCode.setCountry(country);
		      areaCode.getState().setCountry(country);
		      return areaCodeRepository.save(areaCode);
		    }).orElseThrow(() -> new CountryNotFoundException(countryId));
		
		    return new ResponseEntity<>(areacode, HttpStatus.CREATED);
		
		//return areaCodeRepository.save(areaCode);
	}
	
	@GetMapping("/countries/{countryId}/areacodes/{areaCodeId}")
	public Optional<AreaCode> getAreaCode(@PathVariable int countryId,@PathVariable int areaCodeId) {
		
		log.debug("getAreaCode: getting areacode for id="+areaCodeId);
		
		Optional<AreaCode> areacode = countryRepo.findById(countryId).map(country -> {
			/*
			 * areaCode.setCountry(country); areaCode.getState().setCountry(country);
			 */
		      return areaCodeRepository.findById(areaCodeId);
		    }).orElseThrow(() -> new CountryNotFoundException(countryId));
		
		return areaCodeRepository.findById(areaCodeId);
	}
	
	@GetMapping("/areacode")
	public Optional<AreaCode> getAreaCodebycode(@RequestParam String areacode) {
		
		log.debug("getAreaCode: getting areacode for id="+areacode);
		
		return areaCodeRepository.findByCode(areacode);
	}
	
	@PutMapping("/areacodes/{areaCodeId}")
	public AreaCode updateAreaCode(@PathVariable int areaCodeId, @RequestBody AreaCode areaCode) {
		
		log.debug("updateAreaCode: delete areacode for id="+areaCodeId);
		
		Optional<AreaCode> dbAreaCode= areaCodeRepository.findById(areaCodeId) ;
		
		if (dbAreaCode.isEmpty())
			log.error("updateAreaCode: areacode not found for id=" + areaCodeId);
		
		return dbAreaCode.isPresent()? areaCodeRepository.save(areaCode): new AreaCode();
	}
	
	@DeleteMapping("/areacodes/{areaCodeId}")
	public void deleteAreaCode(@PathVariable int areaCodeId) {
		
		log.debug("deleteAreaCode: delete areacode for id="+areaCodeId);
		
		Optional<AreaCode> dbAreaCode= areaCodeRepository.findById(areaCodeId) ;
		
		if (dbAreaCode.isEmpty())
			log.error("deleteAreaCode: areacode not found for id=" + areaCodeId);
		
		if (dbAreaCode.isPresent())
			areaCodeRepository.deleteById(areaCodeId);
	}

}