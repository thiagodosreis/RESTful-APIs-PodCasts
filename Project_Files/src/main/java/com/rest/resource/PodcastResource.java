package com.rest.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.domain.Customer;
import com.rest.domain.Podcast;
import com.rest.exception.ErrorMessage;
import com.rest.exception.NotFoundException;
import com.rest.service.PodcastService;

@Path("podcasts")
public class PodcastResource {

	@Autowired
	PodcastService podcastService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Podcast createPodcast(Podcast podcast) {
		podcastService.createPodcast(podcast);
		return podcast;
	}

	@GET
	@Path("{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Podcast getPodcast(@PathParam("id") int id) throws NotFoundException {
		Podcast podcast = podcastService.getPodcast(id);
		return podcast;
	}

	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response getPodcasts(@QueryParam("title") String title) {
		List<Podcast> podcasts;
		
		if(title != null){
			podcasts = podcastService.getPodcasts(title);
		}else{
			podcasts = getAll();
		}
		
		return Response.ok() //200
		.entity(podcasts)
		.header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
		.allow("OPTIONS").build();
		
		//return podcasts;
	}
	
	@Produces("application/json")
	private List<Podcast> getAll() {
		List<Podcast> podCastList = podcastService.getAll();
		return podCastList;
		

	}

	@PUT
	@Path("{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public void updatePodcast(@PathParam("id") int id, Podcast update) {
		podcastService.updatePodcast(id, update);
	}

	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public void deletePodcast(@PathParam("id") int id) {
		podcastService.deletePodcast(id);
	}
}
