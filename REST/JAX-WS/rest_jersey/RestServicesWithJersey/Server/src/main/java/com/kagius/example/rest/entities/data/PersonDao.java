package com.kagius.example.rest.entities.data;

import com.kagius.example.rest.entities.Person;

import java.util.List;

public interface PersonDao {

    List<Person> all();
    
    Person find(String code);
    Person delete(String code);
}
