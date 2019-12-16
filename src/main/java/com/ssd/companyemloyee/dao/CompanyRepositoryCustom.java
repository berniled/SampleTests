package com.ssd.companyemloyee.dao;

import java.util.Optional;

import com.ssd.companyemloyee.entity.Company;


public interface CompanyRepositoryCustom {
	 public Optional<Company> findByName(String name);
	   
}
