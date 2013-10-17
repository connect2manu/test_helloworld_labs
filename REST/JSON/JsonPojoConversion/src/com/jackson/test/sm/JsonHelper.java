package com.jackson.test.sm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.test.sm.model.RECodRequest;

/**
 * @author manu.mehrotra
 */
public class JsonHelper {
	// private static MappingJsonFactory mappingJFactory = new	// MappingJsonFactory();
	private static JsonFactory jfactory = new JsonFactory();

	public static String writeCodRequest() throws IOException {
		JsonGenerator jGenerator = null;
		OutputStream out = null;
		String jsonOutputStr = null;

		/*
		 * write to OutputStream 
		 */
		out = new ByteArrayOutputStream();
		jGenerator = jfactory.createGenerator(out, JsonEncoding.UTF8);

		System.out.println("Writing json...");

		jGenerator.writeStartObject(); // {
		jGenerator.writeObjectFieldStart("RECodRequest");
		generateCodRequestAttributes(jGenerator);
		jGenerator.writeEndObject(); // }

		jGenerator.close();
		jsonOutputStr = out.toString();
		out.close();
		System.out.println(jsonOutputStr);
		return jsonOutputStr;
	}

	public static RECodRequest readCodRequest(String json) throws JsonParseException, NumberFormatException,
			JsonMappingException, IOException {

		JsonParser jParser = null;
		RECodRequest codRequest = new RECodRequest();

		/*** read json string ***/
		jParser = jfactory.createParser(json);

		System.out.println("Reading json...");
		
		ObjectMapper mapper = new ObjectMapper();

		// loop until token equal to "}"
		while (jParser.nextToken() != JsonToken.END_OBJECT) {

			String fieldName = jParser.getCurrentName();
			System.out.println("Reading json attribute: fieldName = " + fieldName);

			if (fieldName == null) {
				continue;
			}

			jParser.nextToken();

			if ("accountName".equals(fieldName)) {
				codRequest.setAccountName(jParser.getText());
				// System.out.println(jParser.getText()); // display name
			}

			if ("optIn".equals(fieldName)) {
				codRequest.setOptIn(Boolean.valueOf(jParser.getText()));
			}

			if ("deviceId".equals(fieldName)) {
				codRequest.setDeviceId(jParser.getText());
			}

			if ("userId".equals(fieldName)) {
				codRequest.setUserId(jParser.getText());
			}

			if ("maxResults".equals(fieldName)) {
				codRequest.setMaxResults(Integer.valueOf(jParser.getText()));
			}

			if ("insertItems".equals(fieldName)) {
				codRequest.setInsertItems(Boolean.valueOf(jParser.getText()));
			}

			if ("searchString".equals(fieldName)) {
				codRequest.setSearchString(jParser.getText());
			}

			if ("preferences".equals(fieldName)) {
				Map<String, String> mapObject = mapper.readValue(jParser, Map.class);
				codRequest.setPreferences(mapObject);
			}

			if ("contentItems".equals(fieldName)) {
				List<Integer> contentIdsList = mapper.readValue(jParser, List.class);
				codRequest.setContentItems(contentIdsList);
				 // contentItems is array, loop until token equal to "]"
//				 while (jParser.nextToken() != JsonToken.END_ARRAY) {
//					codRequest.addContentItem(Integer.valueOf(jParser.getText()));
//				 }
			}
		}

		System.out.println(codRequest);
		return codRequest;
	}

	public static List<Integer> readCodResponse(String jsonString) {
		return Collections.emptyList();
	}

	public static String writeEpgRequest() throws IOException {
		return null;
	}

	public static List<Integer> readEpgRequest(String jsonString) {
		return Collections.emptyList();
	}

	public static List<Integer> readEpgResponse(String jsonString) {
		return Collections.emptyList();
	}

	private static void generateCodRequestAttributes(JsonGenerator jGenerator) throws JsonGenerationException,
			IOException {
		ObjectMapper mapper = new ObjectMapper();

		// "accountName" : "MyAccountName"
		jGenerator.writeStringField("accountName", "MyAccountName");
		// "optIn" : true
		jGenerator.writeBooleanField("optIn", true);
		// "deviceId" : "ABC12345"
		jGenerator.writeStringField("deviceId", "ABC12345");
		// "userId" : "111"
		jGenerator.writeStringField("userId", "111");
		// "maxResults" : 10
		jGenerator.writeNumberField("maxResults", 10);
		// "insertItems" : false
		jGenerator.writeBooleanField("insertItems", false);
		// "searchString" : "Abc"
		jGenerator.writeStringField("searchString", "Abc");

		// "items" :
		jGenerator.writeFieldName("contentItems");
//		jGenerator.writeStartArray(); // [
		List<Integer> contentIdsList = new ArrayList<Integer>();
		// jGenerator.writeString("1"); // "1"
		// jGenerator.writeString("2"); // "2"
		contentIdsList.add(1);
		contentIdsList.add(2);
		contentIdsList.add(3);
		contentIdsList.add(4);
		mapper.writeValue(jGenerator, contentIdsList);
//		jGenerator.writeEndArray(); // ]
		
		// "preferences":
		jGenerator.writeFieldName("preferences");
//		jGenerator.writeStartArray(); // [
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("pref1", "value1");
		mapObject.put("pref2", "value2");
		mapObject.put("pref3", "value3");
		mapper.writeValue(jGenerator, mapObject);
//		jGenerator.writeEndArray(); // ]
	}

	public static void main(String[] a) throws IOException {
		JsonHelper.readCodRequest(JsonHelper.writeCodRequest());
	}

}
