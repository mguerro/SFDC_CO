package com.accenture.co.DTOs;

import java.io.Serializable;

public class QueryWrapper implements Serializable{

	private String query;
	private String header;
	private String queryType;
	
	public QueryWrapper(String query, String header, String queryType) {
		this.query = query;
		this.header = header;
		this.queryType = queryType;
	}

	public String getQuery() {
		return query;
	}

	public String getHeader() {
		return header;
	}

	public String getQueryType() {
		return queryType;
	}
	
}
