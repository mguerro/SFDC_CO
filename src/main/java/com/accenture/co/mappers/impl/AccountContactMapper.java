package com.accenture.co.mappers.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.mule.module.json.JsonData;

import com.accenture.co.interfaces.Constants;
import com.accenture.co.interfaces.Mapper;

public class AccountContactMapper implements Mapper,Constants{

	Map<String,String>exports = new TreeMap<String,String>();		
	Set<StringBuilder>accounts = new HashSet<StringBuilder>();
	Set<StringBuilder>contacts = new HashSet<StringBuilder>();
	
	@Override
	public Map<String,String> populateCSV(JsonData json) {
				
		ArrayNode records = (ArrayNode)json.get("records");
		
		generateAccounts(records);
		
		/*		
		String accountCsv = accounts.stream().map(e -> e.toString()).reduce("", String::concat);
		String contactCsv = contacts.stream().map(e -> e.toString()).reduce("", String::concat);
		*/
		
		exports.put("account", listToCsv(accounts));
		exports.put("contact", listToCsv(contacts));
		
		return exports;
	}

	private void generateAccounts(ArrayNode records) {
		
		for(JsonNode record : records){	
			
			StringBuilder accountCsvBuilder = new StringBuilder();
			
			accountCsvBuilder
				.append(record.get("Id").asText())	
				.append(DELIMITER)
				.append(record.get("Name").asText())	
				.append(DELIMITER)
				.append(record.get("Description").asText())
				.append("\n");
				
				accounts.add(accountCsvBuilder);
				
				JsonNode contactNode = record.get("Contacts");			
				generateContacts(contactNode);			
		}
	}

	private void generateContacts(JsonNode contact) {
		
		if (!contact.isNull()){
			
			StringBuilder contactCsvBuilder = new StringBuilder();
			
			List<JsonNode> idList = contact.findValues("Id");
			List<JsonNode> lastNames = contact.findValues("LastName");
			
			for(int i=0;i<idList.size()-1;i++){
				
				contactCsvBuilder
				.append(idList.get(i).asText())
				.append(DELIMITER)
				.append(lastNames.get(i).asText())
				.append("\n");
				
				contacts.add(contactCsvBuilder);
				
			}			
		}	
	}

	private String listToCsv(Set<StringBuilder> set){
		
		StringBuilder builder = new StringBuilder();
		
		for (StringBuilder entry : set)		
			builder.append(entry);
			
		return builder.toString();
	}

}
