package com.kagius.example.rest.entities.marshaling;

import com.kagius.example.rest.entities.Person;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class PersonSummary implements Person {

	private String name;
	private String surname;
	private String code;

	// Default constructor used for marshaling.
	public PersonSummary() {
	}

	public PersonSummary(String name, String surname, String code) {
		this.name = name;
		this.surname = surname;
		this.code = code;
	}

	// Property accessors
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// Utility methods
	@Override
	public String toString() {
		return code;
	}
}
