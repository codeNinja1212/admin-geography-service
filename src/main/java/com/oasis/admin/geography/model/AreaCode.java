package com.oasis.admin.geography.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class AreaCode {

	@Id
	@NonNull
	private Integer id;

	@NonNull
	private java.lang.String code;
	
	
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY,optional=false)
	 * 
	 * @JsonIgnore
	 * 
	 * @JoinColumn(name = "country_id") private Country country;
	 */


	@OneToOne(cascade = { CascadeType.ALL })
	@NonNull
	private State state;

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Set<String> counties;

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Set<String> cities;

	/*
	 * @OneToMany (mappedBy="areaCode")
	 * 
	 * @NonNull private Set<Timezone> timezones;
	 */

	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  
	private GeographyImport geoImport;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Integer refId;
}
