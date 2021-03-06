package com.atguigu.springboot01.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class JsonDoubleSerializer extends JsonSerializer<Double> {
	@Override
	public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if (value == null)
			if (value == null) {
				gen.writeString(""); // ROUND_HALF_UP四舍五入
			}
	}
}