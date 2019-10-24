package com.depromeet.warmup1.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class UtilEncoder {
	public String encoding(String target) {
		String result = Base64.getEncoder().encodeToString(target.getBytes());
		return result;
	}
	
	public String decoding(String target) {
		byte[] decodedBytes = Base64.getDecoder().decode(target);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
}
