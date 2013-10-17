package com.jackson.test.databinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({
    "name",
    "age",
    "messages",
    "preferences",
    "items"
})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int age;
	
	private String name;
	
	private String[][] items;
	
	private List<String> messages = new ArrayList<String>();
	
	private Map<String, String> preferences = new HashMap<String, String>();

	/**
	 * @return the items
	 */
	public String[][] getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(String[][] items) {
		this.items = items;
	}

	/**
	 * @return the preferences
	 */
	public Map<String, String> getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences
	 *            the preferences to set
	 */
	public void setPreferences(Map<String, String> preferences) {
		this.preferences = preferences;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @param message
	 */
	public void addMessage(String message) {
		messages.add(message);
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [age=");
		builder.append(age);
		builder.append(", name=");
		builder.append(name);
		builder.append(", items=");
		builder.append(Arrays.toString(items));
		builder.append(", messages=");
		builder.append(messages);
		builder.append(", preferences=");
		builder.append(preferences);
		builder.append("]");
		return builder.toString();
	}

}
