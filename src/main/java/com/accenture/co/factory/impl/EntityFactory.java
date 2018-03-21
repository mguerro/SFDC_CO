package com.accenture.co.factory.impl;

import com.accenture.co.interfaces.Mapper;
import com.accenture.co.mappers.impl.AccountContactMapper;

public class EntityFactory {
	 
	 public static Mapper createMapperType(String type){
		 Mapper mapper = null;
	     if ("contact-account".equals(type) ){
	        mapper = new AccountContactMapper();
	     }else if ("other".equals(type)){
	    	 //mapper = new ContactMapper();
	     }else{
	       throw new IllegalArgumentException("Unknown entity");
	     }
	     return mapper;
	    }
	}

