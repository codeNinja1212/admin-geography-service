package com.oasis.admin.geography.model;

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
public class Language {

	/** identifier field */
	@Id
	@NonNull
	private Integer languageId;

	public static final String ALPHA2 = "alpha2";

	/** nullable persistent field */
	private String alpha2;

	public static final String ALPHA3 = "alpha3";

	/** persistent field */
	private String alpha3;

	@NonNull
	public static final String NAME = "name";

	/** persistent field */
	private String name;

	/** nullable persistent field */
	private String description;

	public static final String GEO_IMPORT = "geoImport";

	@ManyToOne
	private GeographyImport geoImport;

	public static final String REF_ID = "refId";

	private Integer refId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JsonIgnore
	@JoinColumn(name = "country_id")
	private Country country;
}
