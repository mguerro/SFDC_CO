package com.accenture.co.interfaces;

import java.util.Map;
import org.mule.module.json.JsonData;

public interface Mapper {

	public abstract Map<String, String> populateCSV(JsonData json);
	
}
