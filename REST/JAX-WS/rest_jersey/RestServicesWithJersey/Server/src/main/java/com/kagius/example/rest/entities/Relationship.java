package com.kagius.example.rest.entities;

public interface Relationship {

    Person getFrom();
    Person getTo();
    String getType();
}
