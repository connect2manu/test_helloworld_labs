package com.kagius.example.rest.actions.people;

import com.kagius.example.rest.entities.marshaling.PersonList;
import com.kagius.example.rest.entities.data.PersonDao;
import com.sun.jersey.spi.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/people")
@Scope("request")
public class Group {

	@Inject("people")
	private PersonDao dao;

	@GET
	@Produces({ "application/json" })
	public Object list() {
		return new PersonList(dao.all());
	}

}
