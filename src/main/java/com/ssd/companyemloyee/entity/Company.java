package com.ssd.companyemloyee.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Company {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;	
	 
	 private String name;
	 private String address;
	 private boolean active;
	 private boolean deleted;
	 
	 
	 
	 @OneToMany
	 @JoinColumn(name="company_id")
	 private Collection<Employee> employees;
	 
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String companyName) {
		this.name = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String companyAddress) {
		this.address = companyAddress;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

	public Company(String name, String addr, boolean active, boolean deleted) {
		 this.name = name;
		 this.address = addr;
		 this.active = active;
		 this.deleted = deleted;
	 }
	
	public Company() {
		
	}
}
