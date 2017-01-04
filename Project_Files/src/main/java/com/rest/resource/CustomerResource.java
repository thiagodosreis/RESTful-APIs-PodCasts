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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.domain.Customer;
import com.rest.domain.CustomerV2;
import com.rest.exception.ErrorMessage;
import com.rest.exception.NotFoundException;


@Path("customers")
public class CustomerResource {
	
	// ConcurrentHashMap - A hash table supporting full concurrency of retrievals and 
	//adjustable expected concurrency for updates.
	static private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
	// An AtomicInteger is used in applications such as atomically incremented counters
	static public AtomicInteger idCounter = new AtomicInteger();

	
	@POST
	@Consumes({ "application/xml", "application/json" })
	public Customer createCustomer(Customer customer) {
	 customer.id = idCounter.incrementAndGet();
	 customerDB.put(customer.id, customer);
	 return customer;
	}
		
	
	@GET
	@Path("{id}")
	@Produces({ "application/xml", "application/json" })
	public Customer getCustomer(@PathParam("id") int id) {
	 Customer customer = customerDB.get(id);
	 if (customer == null) {
		 ErrorMessage errorMessage = new ErrorMessage("1001", "Customer not found!", "http://localhost:8080/lab3/error1001.jsp",
		 Response.Status.NOT_FOUND);
		 throw new NotFoundException(errorMessage);
	 }
	 else
		 return customer;
	}
	
	@GET
	@Produces({ "application/xml", "application/json" })
	public Collection<Customer> getAll() {
		List<Customer> customerList = new ArrayList<Customer>(customerDB.values());
		return customerList;
	}
	
	@PUT
	@Path("{id}")
	@Consumes({ "application/xml", "application/json" })
	public void updateCustomer(@PathParam("id") int id, Customer update) {
	 Customer current = customerDB.get(id);
	 current.firstname = update.firstname;
	 current.lastname = update.lastname;
	 current.email = update.email;
	 customerDB.put(current.id, current);
	}
	
	
	@DELETE
	@Path("{id}")
	public void deleteCustomer(@PathParam("id") int id) {
	 Customer current = customerDB.remove(id);
	 if (current == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
	 }
}
