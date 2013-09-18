package com.kagius.example.rest.actions.people;

import com.kagius.example.rest.entities.*;
import com.kagius.example.rest.entities.data.PersonDao;
import com.kagius.example.rest.entities.marshaling.PersonDetail;
import com.kagius.example.rest.entities.marshaling.PersonSummary;
import com.kagius.example.rest.entities.marshaling.RelationshipSummary;
import com.sun.jersey.spi.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;

@Component
@Scope("request")
@Path("/people/{code}")
public class Individual {

	@Inject("people")
	private PersonDao dao;

	@GET
	@Produces({ "application/json", "application/xml" })
	public Object find(@PathParam("code") String code) {

		return dao.find(code);
	}

	@DELETE
	public void remove(@PathParam("code") String code) {
		dao.delete(code);
	}
}
