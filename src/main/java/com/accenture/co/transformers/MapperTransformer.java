package com.accenture.co.transformers;

import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.module.json.JsonData;
import org.mule.transformer.AbstractMessageTransformer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.accenture.co.factory.impl.EntityFactory;
import com.accenture.co.interfaces.Mapper;
import com.accenture.co.strategies.interfaces.MapperStrategy;

public class MapperTransformer extends AbstractMessageTransformer{
	
	//private MapperStrategy mapperStrategy;
	private EntityFactory entityFactory;
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		
		JsonData payload = (JsonData) message.getPayload();
		//using spring
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-application_context.xml");
		//using Java
		//Mapper mapper = (Mapper) context.getBean((String)message.getInvocationProperty("queryType"));
		Mapper mapper = entityFactory.createMapperType((String)message.getInvocationProperty("queryType"));
		//Mapper mapper = mapperStrategy.getMapper(message.getInvocationProperty("queryType"));
		Map<String, String> csv = mapper.populateCSV(payload);
		message.setPayload(csv);
		
		return message;
	}

//	public MapperStrategy getMapperStrategy() {
//		return mapperStrategy;
//	}
//
//	public void setMapperStrategy(MapperStrategy mapperStrategy) {
//		this.mapperStrategy = mapperStrategy;
//	}
}
