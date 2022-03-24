package com.oasis.admin.geography.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oasis.admin.geography.util.PhoneNumberingPlanType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Country {

	/** identifier field */
	@Id
	@NonNull
	private Integer id;

	@NonNull
	private String alpha2;

	@NonNull
	private String alpha3;

	@NonNull
	private String name;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String description;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String cbrCountryCode;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String phoneInternationalAccess;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String phoneNationalAccess;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Integer phoneMaxDigits;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Integer phoneMinDigits;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String phoneCountryCode;

	@OneToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private PhoneNumberingPlanType phoneNumberingPlan;

	/*
	 * @OneToMany
	 * 
	 * @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) private Set<Timezone>
	 * timezones;
	 */

	/*
	 * @OneToMany
	 * 
	 * @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) private Set<Language>
	 * languages;
	 */
	/*
	 * @OneToMany
	 * 
	 * @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) private Set<Currency>
	 * currencies;
	 */

	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private GeographyImport geoImport;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Integer refId;
}
