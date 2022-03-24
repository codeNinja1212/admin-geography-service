package com.oasis.admin.geography.exceptions;

public class CountryNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(long id) {
		super("Could not find Country " + id);;
	}

}
