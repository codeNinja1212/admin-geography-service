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
public class Currency {

	public Currency(String string) {
		// TODO Auto-generated constructor stub
	}

	private static Currency defaultExample = new Currency("USD");

	/** identifier field */
	@Id
	@NonNull
	private Integer currencyId;

	public static final String ALPHA3 = "alpha3";

	/** nullable persistent field */
	private String alpha3;

	public static final String NAME = "name";

	/** nullable persistent field */
	private String name;

	public static final String NUM = "num";

	/** nullable persistent field */
	private Integer num;

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
