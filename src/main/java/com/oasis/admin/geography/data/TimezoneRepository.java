package com.oasis.admin.geography.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis.admin.geography.model.Timezone;

public interface TimezoneRepository extends JpaRepository<Timezone, Integer> {
	
	List<Timezone> findByAreaCodeId(Integer areacodeId);

}
