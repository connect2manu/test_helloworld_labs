package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}

	// for TEXT_PLAIN request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello";
	}

	// for HTML request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html>" + "<title>" + "Hello Jersey html" + "</title>" + "<body><h1>" + "Hello Jersey html"
				+ "</body></h1>" + "</html>";
	}

	@GET
	@Produces("application/json") // MediaType.APPLICATION_JSON
	@Path("/network/{id: [0-9]+}/{nid}")
	public Response getUserByNetworkId(@PathParam("id") int id, @PathParam("nid") String networkId) {
		// Query q =
		// em.createQuery("SELECT u FROM User u WHERE u.networkId = :id AND u.networkUserId = :nid");
		// q.setParameter("id", id);
		// q.setParameter("nid", networkId);
		// return (User) q.getSingleResult();
		String output = "id : " + id + ", networkId : " + networkId;

		return Response.status(200).entity(output).build();
	}

	/*    @PUT
	    @Consumes("application/json")
	    @Produces("application/json")
	    @Path("/create")
	    public User createUser(User user) {
	        user.setNetwork(em.getReference(Network.class, user.getNetworkId()));
	        em.persist(user);
	        em.refresh(user);
	        return user;
	    }
	    */
 
}
