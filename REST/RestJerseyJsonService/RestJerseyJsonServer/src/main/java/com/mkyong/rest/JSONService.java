package com.mkyong.rest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mkyong.ContentType;
import com.mkyong.FolderType;
import com.mkyong.Movie;
import com.mkyong.PrikbordCreateResponse;
import com.mkyong.PrikbordGetTreeResponse;
import com.mkyong.PrikbordItem;
import com.mkyong.ReferenceType;
import com.mkyong.TGeneralPrikbordErrorResponse;
import com.mkyong.TStatus;
import com.mkyong.Track;

@Path("/prikbord")
public class JSONService {

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {
		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");
		return track;
	}

	@GET
	@Path("/movie/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMovieInJSON() {
		Movie movie = new Movie();
		movie.setName("Transformers: Dark of the Moon");
		movie.setDirector("Michael Bay");
		movie.setYear(2011);
		return movie;
	}

	@POST
	@Path("/movie/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track) {
		String result = "Track saved : " + track;
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public PrikbordCreateResponse createPrikbordResponse() {
		PrikbordCreateResponse pbResponse = new PrikbordCreateResponse();
		pbResponse.setStatus(TStatus.SUCCESS);
		return pbResponse;
	}

	@GET
	@Path("/error")
	@Produces(MediaType.APPLICATION_JSON)
	public TGeneralPrikbordErrorResponse createPrikbordErrorResponse() {
		TGeneralPrikbordErrorResponse pbError = new TGeneralPrikbordErrorResponse();
		pbError.setErrorCode(new BigInteger("1001"));
		pbError.setErrorDescription("Prickbord Error");
		return pbError;
	}

	@GET
	@Path("/get/tree")
	@Produces(MediaType.APPLICATION_JSON)
	public PrikbordGetTreeResponse createPrikbordTreeResponse() {
		PrikbordGetTreeResponse pbTree = new PrikbordGetTreeResponse();
		List<PrikbordItem> prikbordItemData = new ArrayList<PrikbordItem>();
		PrikbordItem item1 = new PrikbordItem();
		item1.setContentType(ContentType.AUDIO);
		item1.setDescription("Item1 Desc");
		item1.setFolderType(null);
		item1.setItemId(111);
		item1.setParentalRating("PG");
		item1.setParentId(11);
		item1.setPosterFile("\\etc\\images\\posters\\item1_poster.png");
		item1.setPrikbordId(1);
		item1.setReference("PB-Item1-Reference");
		item1.setReferenceType(ReferenceType.VOD);
		item1.setTitle("Item 1");
		prikbordItemData.add(item1);

		PrikbordItem item2 = new PrikbordItem();
		item2.setContentType(null);
		item2.setDescription("Item2 Desc");
		item2.setFolderType(FolderType.STATIC);
		item2.setItemId(222);
		item2.setParentalRating("PG");
		item2.setParentId(22);
		item2.setPosterFile("\\etc\\images\\posters\\item2_poster.png");
		item2.setPrikbordId(2);
		item2.setReference("PB-Item2-Reference");
		item2.setReferenceType(ReferenceType.FOLDER);
		item2.setTitle("Item 2 - Folder");
		prikbordItemData.add(item2);

		pbTree.setPrikbordItemData(prikbordItemData);
		return pbTree;
	}


}