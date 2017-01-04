package com.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {
	// Maps a object property to a XML element derived from property name.
	public int id;
	public String firstname;
	public String lastname;
	public String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

/*
<customer>
<firstname>Bill</firstname>
<lastname>Burke</lastname>
<email>bill.burke@gmail.com</email>
</customer>


{
  "firstname": "Thiago",
  "lastname": "Reis",
  "email": "thiago@test.com"
}
*/