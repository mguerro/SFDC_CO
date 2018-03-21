package com.accenture.co.strategies.interfaces;

import com.accenture.co.interfaces.Mapper;

public interface MapperStrategy {
	
	public Mapper getMapper(String queryType);
		
}
