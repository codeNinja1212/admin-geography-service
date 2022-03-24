package com.oasis.admin.geography;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TimeZone;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.oasis.admin.geography.data.AreaCodeRepository;
import com.oasis.admin.geography.data.TimezoneRepository;
import com.oasis.admin.geography.model.AreaCode;
import com.oasis.admin.geography.model.Country;
import com.oasis.admin.geography.model.State;
import com.oasis.admin.geography.model.Timezone;

@SpringBootApplication
public class GeographyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeographyApplication.class, args);
	}
	
	/*
	 * @Bean public ApplicationRunner dataLoder(TimezoneRepository tzRepo,
	 * AreaCodeRepository aRepo) { return args -> { //Timezone tz = tzRepo.save(new
	 * Timezone(15,"Eastern Time", true));
	 * 
	 * //aRepo.save(new AreaCode(5062,"201",new Country(840,"US", "USA",
	 * "United States of America"), new State(5055,"NEW JERSEY", "NJ"), new
	 * HashSet<Timezone>(Arrays.asList(tz)))); //aRepo.save(new
	 * AreaCode(25073,"770",new Country(840,"US", "USA",
	 * "United States of America"), new State(25066,"GEORGIA", "GA"), new
	 * HashSet<Timezone>(Arrays.asList(tz))));
	 * 
	 * aRepo.save(new AreaCode(5062,"201",new Country(840,"US", "USA",
	 * "United States of America"), new State(5055,"NEW JERSEY", "NJ"), new
	 * HashSet<Timezone>(Arrays.asList(new Timezone(15,"Eastern Time", true)))));
	 * aRepo.save(new AreaCode(25073,"770",new Country(840,"US", "USA",
	 * "United States of America"), new State(25066,"GEORGIA", "GA"), new
	 * HashSet<Timezone>(Arrays.asList(new Timezone(15,"Eastern Time", true))))); };
	 * }
	 */
}
