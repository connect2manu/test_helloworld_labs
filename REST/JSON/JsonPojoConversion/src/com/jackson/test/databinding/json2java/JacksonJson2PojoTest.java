package com.jackson.test.databinding.json2java;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.test.databinding.User;

/**
 * Converts Java Object (POJO) to JSON
 */
public class JacksonJson2PojoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			// read from file, convert it to user class
			User user = mapper.readValue(new File("c:\\user.json"), User.class);

			// display to console
			System.out.println(user);
			System.out.println("User Prefs: " + user.getPreferences());

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
