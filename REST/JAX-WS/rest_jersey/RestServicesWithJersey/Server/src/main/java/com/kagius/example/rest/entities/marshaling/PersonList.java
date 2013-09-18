package com.kagius.example.rest.entities.marshaling;

import com.kagius.example.rest.entities.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
public class PersonList {
	private List<Person> persons;

	public PersonList() {
	}

	public PersonList(List<Person> persons) {
		this.persons = persons;
	}

	@XmlElement(type = PersonSummary.class)
	public List<Person> getPersons() {
		return persons;
	}
}
