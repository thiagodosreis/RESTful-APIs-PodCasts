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
import javax.ws.rs.core.Response;

import com.rest.domain.Customer;
import com.rest.domain.CustomerV2;
import com.rest.exception.ErrorMessage;
import com.rest.exception.NotFoundException;

@Path("v2/customers")
public class CustomerResourceV2{
	
	static private Map<Integer, CustomerV2> customerDBV2 = new ConcurrentHashMap<Integer, CustomerV2>();
	//static protected AtomicInteger idCounter = new AtomicInteger();

	
	@POST
	@Consumes("application/v2+json")
	public CustomerV2 createCustomerV2(CustomerV2 customerV2) {
		customerV2.id = CustomerResource.idCounter.incrementAndGet();
		customerDBV2.put(customerV2.id, customerV2);
		return customerV2;
	}
	
	
	@GET
	@Path("{id}")
	@Produces("application/v2+json")
	public CustomerV2 getCustomerV2(@PathParam("id") int id) {
	 CustomerV2 customerV2 = customerDBV2.get(id);
	 if (customerV2 == null) {
		 ErrorMessage errorMessage = new ErrorMessage("1001", "Customer not found!", "http://localhost:8080/lab3/error1001.jsp",
		 Response.Status.NOT_FOUND);
		 throw new NotFoundException(errorMessage);
	 }
	 else
		 return customerV2;
	}
	
	@GET
	@Produces("application/v2+json")
	public Collection<CustomerV2> getAllV2() {
		List<CustomerV2> customerListV2 = new ArrayList<CustomerV2>(customerDBV2.values());
		return customerListV2;
	}
	 
	@PUT
	@Path("{id}")
	@Consumes("application/v2+json")
	public void updateCustomerV2(@PathParam("id") int id, CustomerV2 update) {
	 CustomerV2 current = customerDBV2.get(id);
	 current.name = update.name;
	 current.contactNumber = update.contactNumber;
	 current.email = update.email;
	 customerDBV2.put(current.id, current);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteCustomerV2(@PathParam("id") int id) {
	 CustomerV2 current = customerDBV2.remove(id);
	 if (current == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
	 }

}
