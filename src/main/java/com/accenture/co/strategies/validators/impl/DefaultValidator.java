package com.accenture.co.strategies.validators.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.EnumUtils;
import com.accenture.co.enums.QueryTypeEnum;
import com.accenture.co.strategies.exceptions.BadCredentialsOathException;
import com.accenture.co.strategies.exceptions.BadQueryTypeException;
import com.accenture.co.strategies.validators.interfaces.Validator;

public class DefaultValidator implements Validator{

	@Override
	public void credentialsValidator(String username, String password, String clientId, String clientSecret, String endpoint) throws BadCredentialsOathException {
				
		boolean isValidUsername = !StringUtils.isEmpty(username);
		boolean isValidPassword = !StringUtils.isEmpty(password);
		boolean isValidClientId = !StringUtils.isEmpty(clientId);
		boolean isValidClientSecret = !StringUtils.isEmpty(clientSecret);
		boolean isValidEndpoint = !StringUtils.isEmpty(endpoint);
		
		if (!isValidUsername || !isValidPassword || !isValidClientId || !isValidClientSecret || !isValidEndpoint)
			throw new BadCredentialsOathException("Error: bad credentials: Username: " + username + " Password: " + password + " Client id: " + clientId + "Client secret: "+clientSecret + " Endpoint: " + endpoint);	
	}

	@Override
	public void queryValidator(String query, String queryType, String csvHeader) throws BadQueryTypeException {
		
		//TODO more accurate checks
		
		boolean isValidQuery = !StringUtils.isEmpty(query);
		boolean isValidQueryType = EnumUtils.isValidEnum(QueryTypeEnum.class, queryType.toUpperCase());
		boolean isValidHeader = !StringUtils.isEmpty(csvHeader);
		
		if (!isValidQuery || !isValidQueryType || !isValidHeader){		
			throw new BadQueryTypeException("Error: bad query parameters: Query: " + query + " Query Type: " + queryType + " Header: "+csvHeader);		
		}	
	}	
}
