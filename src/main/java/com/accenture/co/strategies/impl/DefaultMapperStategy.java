package com.accenture.co.strategies.impl;

import java.util.Map;

import com.accenture.co.interfaces.Mapper;
import com.accenture.co.strategies.interfaces.MapperStrategy;

public class DefaultMapperStategy implements MapperStrategy{
	
	private Map<String,Mapper> implementationsMap;
		
	@Override
	public Mapper getMapper(String queryType) {
						
		return implementationsMap.get(queryType);
	}

	public Map<String, Mapper> getImplementationsMap() {
		return implementationsMap;
	}

	public void setImplementationsMap(Map<String, Mapper> implementationsMap) {
		this.implementationsMap = implementationsMap;
	}

}
