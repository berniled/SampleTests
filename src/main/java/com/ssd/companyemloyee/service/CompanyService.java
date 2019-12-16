package com.ssd.companyemloyee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.ssd.companyemloyee.dao.CompanyRepository;
import com.ssd.companyemloyee.entity.Company;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepo;

	/**
	 * This will retrieve all the 
	 * @return
	 */
	public List<Company> findAll() {
	    return companyRepo.findAll();
	}
	
	public Company findCompanyByName(String name) {
		name = HtmlUtils.htmlEscape(name);
		Optional<Company> comp = companyRepo.findByName(name);
		return comp.orElse(new Company());
	}
	
	/**
	 * This method will activate the company
	 * @param name
	 * @return Activated company
	 */
	public Company activateCompany(String name) {
		Company comp = findCompanyByName(name);
		comp.setActive(true);
		return companyRepo.save(comp);
	}
	
	/**
	 * This method will deactivate the company
	 * @param name
	 * @return Deactivated company
	 */
	public Company deactivateCompany(String name) {
		Company comp = findCompanyByName(name);
		comp.setActive(false);
		return companyRepo.save(comp);
	}
	
	/**
	 * This method will update the company aside from the active and delete fields
	 * 
	 * @param name
	 * @return Updated company
	 */
	public Company updateCompany(Company company) {
		//set the active field to true in case 
		company.setActive(true);
		//set the deleted field to false in case
		company.setDeleted(false);
		return companyRepo.save(company);
	}
	
	
	/**
	 * This method will soft delete the company
	 * 
	 * @param Company comp
	 * @return Soft deleted comp
	 */
	public Company deleteCompany(Company comp) {
		comp.setDeleted(true);
		return companyRepo.save(comp);
	}
	
	/**
	 * This method will undelet the company
	 * 
	 * @param Company comp
	 * @return Undelete comp
	 */
	public Company undeleteCompany(Company comp) {
		comp.setDeleted(false);
		return companyRepo.save(comp);
	}
	
	/**
	 * This will create a new company
	 */
	public Company createCompany(Company comp) {
		return companyRepo.save(comp);
	}
}
