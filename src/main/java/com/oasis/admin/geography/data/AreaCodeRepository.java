package com.oasis.admin.geography.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis.admin.geography.model.AreaCode;

public interface AreaCodeRepository extends JpaRepository<AreaCode, Integer>{
	
	Optional<AreaCode> findByCode(String code);
	
	//List<AreaCode> findByCountryId(Integer countryId);

}
