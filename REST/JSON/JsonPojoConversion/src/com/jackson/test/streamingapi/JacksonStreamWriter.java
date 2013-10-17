package com.jackson.test.streamingapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonStreamWriter {

	public static String write() throws Exception {
		JsonGenerator jGenerator = null;
		OutputStream out = null;
		String jsonOutputStr = null;
		try {
			JsonFactory jfactory = new JsonFactory();

			/*** write to OutputStream ***/
			out = new ByteArrayOutputStream();
			jGenerator = jfactory.createJsonGenerator(out, JsonEncoding.UTF8);

			System.out.println("Writing json...");

			jGenerator.writeStartObject(); // {
			// jGenerator.writeObjectFieldStart("MyJsonRequest");
			populateJsonAttributes(jGenerator);
			jGenerator.writeEndObject(); // }

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jGenerator.close();
			jsonOutputStr = out.toString();
			out.close();
			System.out.println(jsonOutputStr);
		}
		return jsonOutputStr;
	}

	private static void populateJsonAttributes(JsonGenerator jGenerator) throws JsonGenerationException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		jGenerator.writeStringField("name", "TestName"); // "name" : "TestName"
		jGenerator.writeNumberField("age", 30); // "age" : 30

		jGenerator.writeFieldName("messages"); // "messages" :
		jGenerator.writeStartArray(); // [
		jGenerator.writeString("msg 11"); // "msg 1"
		jGenerator.writeString("msg 22"); // "msg 2"
		jGenerator.writeEndArray(); // ]

		jGenerator.writeFieldName("preferences"); // "messages" :
		jGenerator.writeStartArray(); // [
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("pref1", "value1");
		mapObject.put("pref2", "value2");
		mapper.writeValue(jGenerator, mapObject);
		jGenerator.writeEndArray(); // ]
	}

	public static void main(String[] a) throws Exception {
		JacksonStreamWriter.write();
	}
}
