package com.accenture.co.services.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;
import org.springframework.beans.factory.annotation.Value;

import com.accenture.co.DTOs.QueryWrapper;

public class ConfigurationService extends AbstractMessageTransformer{
		
	@Value("${external.properties.location}")
	private String location;	
	@Value("${external.properties.filename}")
	private String filename;
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		
		String filePath = location + filename;
		
		Properties externalconfig = generateProperties(filePath);
		String queryFile = externalconfig.getProperty("system.source.queries.export");
		Properties externalQueries = generateProperties(queryFile);
					
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap = generateMap(externalconfig);
		propertiesMap.putAll(generateQueryWrapperMap(externalQueries));
		
		message.addProperties(propertiesMap,PropertyScope.SESSION);
		
		return message;
	}

	private Properties generateProperties(String filePath) {
		
		InputStream in = null;
		Properties prop = new Properties();
		
		try {
			in = new FileInputStream(filePath);
			prop.load(in);
		} catch (FileNotFoundException e) {
			logger.error(e);
		}
		catch (IOException e) {
			logger.error("Error: ",e);
		}
		
		return prop;
	}
	
	private Map<String, Object> generateMap(Properties prop) {
		
		Map <String,Object> propertiesMap = new HashMap<String,Object>();	
		for (final String name: prop.stringPropertyNames())
			propertiesMap.put(name, prop.getProperty(name));
	
		return propertiesMap;
	}
		
	private Map<String,Object> generateQueryWrapperMap(Properties prop){

		Map <String,Object> propertiesMap = new HashMap<String,Object>();
		Map <String,Object> operations = new HashMap<String,Object>();
		//needs a better way here
		int innerMapObjectSize = prop.size() / 3;
		
		for (int i = 0; i < innerMapObjectSize; i++){
			
			String query = prop.getProperty("operation"+i+".query");
			String header = prop.getProperty("operation"+i+".header");
			String queryType = prop.getProperty("operation"+i+".query_type");
			
			propertiesMap.put("operation"+i,new QueryWrapper(query, header, queryType));
			
		}
		
		operations.put("operations", propertiesMap);
		
		return operations ;
	
	}
}


