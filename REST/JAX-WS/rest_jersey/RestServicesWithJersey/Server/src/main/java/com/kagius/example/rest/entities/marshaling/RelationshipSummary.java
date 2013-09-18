package com.kagius.example.rest.entities.marshaling;

import com.kagius.example.rest.entities.Person;
import com.kagius.example.rest.entities.Relationship;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "relationship")
public class RelationshipSummary implements Relationship {
	private Person from;
	private Person to;
	private String type;

	// Default constructor required for marshaling.
	public RelationshipSummary() {
	}

	public RelationshipSummary(Person from, Person to, String type) {
		this.from = from;
		this.to = to;
		this.type = type;
	}

	@XmlTransient
	public Person getFrom() {
		return from;
	}

	public void setFrom(Person from) {
		this.from = from;
	}

	@XmlElement(type = PersonSummary.class)
	public Person getTo() {
		return to;
	}

	public void setTo(Person to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
