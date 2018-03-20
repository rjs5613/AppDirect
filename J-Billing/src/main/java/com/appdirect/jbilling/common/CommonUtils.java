package com.appdirect.jbilling.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

	/**
	 * 
	 * @param p
	 * @return
	 * @throws JsonProcessingException
	 */
	public static byte[] serializeToByteArray(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] valueAsBytes = objectMapper.writeValueAsBytes(obj);
		return valueAsBytes;
	}

}
