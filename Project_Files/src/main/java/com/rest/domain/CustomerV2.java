package com.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerV2 extends Customer {
	public String name;
	public String contactNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	@JsonIgnore
	public String getFirstname() {
		return firstname;
	}
	@Override
	@JsonIgnore
	public String getLastname() {
		return super.getLastname();
	}
}
