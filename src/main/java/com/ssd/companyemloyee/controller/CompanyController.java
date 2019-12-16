package com.ssd.companyemloyee.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssd.companyemloyee.entity.Company;
import com.ssd.companyemloyee.service.CompanyService;


@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	CompanyService service;
	
	  /**
	   * This method will retrieve all companies records available.
	   * 
	   * @return ResponseEntity<List<Company>> - list of Companies.
	   */
	  @RequestMapping(method=RequestMethod.GET, value="/companies")
	  @ResponseBody
	  @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Company>> getAllInventory() {
		  
  	  return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
	  
	@RequestMapping(method=RequestMethod.GET, value="/company/{id}")
	@ResponseBody
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<Company> getCompany(@PathVariable("id") String companyName) {
		   
		  Company comp = service.findCompanyByName(companyName);
		  return new ResponseEntity<>(comp, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addcompany")
	@ResponseBody
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company) {
		   
		  Company comp = service.createCompany(company);
		  return new ResponseEntity<>(comp, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updatecompany/{id}")
	@ResponseBody
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<Company> updateCompany(@PathVariable("id") String companyName, @Valid @RequestBody Company company) {
		   
		  Company comp = service.findCompanyByName(companyName);
		  if (comp.getId().equals(company.getId())) {
			  service.updateCompany(company);
		  }
		  return new ResponseEntity<>(comp, HttpStatus.OK);
	}
	
}
