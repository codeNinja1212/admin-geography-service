package com.oasis.admin.geography.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class City {

	@Id
	@NonNull
	private Integer cityId;
	
	@NonNull
	private String name;

	// private Map<Integer, PostalCodeSummaryTO> postalcodes;

}
