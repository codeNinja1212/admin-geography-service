package com.oasis.admin.geography.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Timezone {

	@Id
	@NonNull
	private Integer timezoneId;

	@NonNull
	private String name;
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String abbrev;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String jvmTimezone;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String description;

	@NonNull
	private Boolean usesDST;
	
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private GeographyImport geoImport;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Integer refId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JsonIgnore
	@JoinColumn(name = "area_code_id", nullable = true)
	private AreaCode areaCode;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JsonIgnore
	@JoinColumn(name = "country_id", nullable=true)
	private Country country;
}
