package com.jackson.test.databinding.java2json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.test.databinding.User;

/**
 * Converts Java Object (POJO) to JSON
 */
public class JacksonPojo2JsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		User user = new User();
		populateUserData(user);

		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert user object to json string, and save to a file
			mapper.writeValue(new File("c:\\user.json"), user);

			// display to console
			System.out.println(mapper/*.defaultPrettyPrintingWriter()*/.writeValueAsString(user));

		} catch (JsonGenerationException ex) {
			ex.printStackTrace();
		} catch (JsonMappingException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}
	}

	private static void populateUserData(User user) {
		user.setAge(30);
		user.setName("UserName");

		List<String> msgs = new ArrayList<String>();
		msgs.add("msg11");
		msgs.add("msg22");
		user.setMessages(msgs);

		Map<String, String> prefs = new HashMap<String, String>();
		prefs.put("Name1", "Value1");
		prefs.put("Name2", "Value2");
		prefs.put("Name3", "Value3");
		user.setPreferences(prefs);

		String[][] items = new String[2][2];
		items[0][0] = "contentId";
		items[0][1] = "1";
		items[1][0] = "contentId";
		items[1][1] = "2";
		user.setItems(items);
	}

}
