package com.oasis.admin.geography.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class State {
	@Id
	@NonNull
	private Integer stateId;

	@NonNull
	private String name;
	
	@NonNull
	private String abbreviation;

	@ManyToOne(fetch = FetchType.EAGER,optional=false)
	//@JsonIgnore
	@JoinColumn(name = "country_id")
	private Country country;

}
