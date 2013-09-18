package com.kagius.example.rest.entities.data;

import com.kagius.example.rest.entities.Person;

import java.util.*;

public class InMemoryPersonDaoImpl implements PersonDao {

    private Map<String, Person> people;

    public InMemoryPersonDaoImpl(Map<String, Person> people) {
        this.people = people;
    }

    public List<Person> all() { return new ArrayList<Person>(people.values()); }

    public Person find(String code) { return people.get(code); }

    public Person delete(String code) { return people.remove(code); }
}
