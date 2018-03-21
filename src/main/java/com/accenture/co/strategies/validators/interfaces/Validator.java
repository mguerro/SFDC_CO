package com.accenture.co.strategies.validators.interfaces;

import com.accenture.co.strategies.exceptions.BadCredentialsOathException;
import com.accenture.co.strategies.exceptions.BadQueryTypeException;

public interface Validator {
	
	public void credentialsValidator(String username, String password, String clientId, String clientSecret, String endpoint) throws BadCredentialsOathException;
	public void queryValidator(String query, String queryType, String csvHeader) throws BadQueryTypeException;
	
}
