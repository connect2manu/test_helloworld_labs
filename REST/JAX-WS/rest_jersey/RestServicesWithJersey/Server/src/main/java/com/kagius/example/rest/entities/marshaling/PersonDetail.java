package com.kagius.example.rest.entities.marshaling;

import com.kagius.example.rest.entities.Person;
import com.kagius.example.rest.entities.Relationship;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "person")
public class PersonDetail extends PersonSummary implements Person {

	private List<Relationship> relationships;

	// Default constructor used for marshaling.
	public PersonDetail() {
	}

	public PersonDetail(String name, String surname, String code, List<Relationship> relationships) {
		super(name, surname, code);
		this.relationships = relationships;
	}

	// Property accessors
	@XmlElement(type = RelationshipSummary.class)
	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
}
