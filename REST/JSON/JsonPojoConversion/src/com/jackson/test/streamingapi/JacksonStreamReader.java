package com.jackson.test.streamingapi;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jackson.test.databinding.User;

public class JacksonStreamReader {

	public static void read(String jsonData) throws Exception {
		JsonParser jParser = null;
		User user = new User();
		try {
//			ByteArrayInputStream is = new ByteArrayInputStream(jsonData.getBytes());
//			ObjectInputStream ois = new ObjectInputStream(is);
			
			// MappingJsonFactory jfactory = new MappingJsonFactory();
			JsonFactory jfactory = new JsonFactory();

			/*** read json string ***/
			jParser = jfactory.createJsonParser(jsonData);
			// user = jParser.readValueAs(User.class);

			System.out.println("Reading json...");

			// loop until token equal to "}"
			while (jParser.nextToken() != JsonToken.END_OBJECT) {

				String fieldName = jParser.getCurrentName();
				System.out.println("Reading json attribute: fieldName = " + fieldName);

				if ("name".equals(fieldName)) {
					// current token is "name",
					// move to next, which is "name"'s value
					jParser.nextToken();
					user.setName(jParser.getText());
					// System.out.println(jParser.getText()); // display name
				}

				if ("age".equals(fieldName)) {
					// current token is "age",
					// move to next, which is "name"'s value
					jParser.nextToken();
					user.setAge(Integer.valueOf(jParser.getText()));
					// System.out.println(jParser.getIntValue()); // display 29
				}

				if ("messages".equals(fieldName)) {
					jParser.nextToken(); // current token is "[", move next
					// messages is array, loop until token equal to "]"
					while (jParser.nextToken() != JsonToken.END_ARRAY) {
						// display msg1, msg2, msg3
						user.addMessage(jParser.getText());
						// System.out.println(jParser.getText());
					}
				}
			}

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jParser.close();
			System.out.println(user);
		}

	}
}
