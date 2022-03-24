package com.oasis.admin.geography.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oasis.admin.geography.model.Country;

public interface CountryRepository extends CrudRepository<Country, Integer>{

	List<Country> findByNameContaining(String name);

}
