package com.ssd.companyemloyee.dao;

import java.util.Optional;

import com.ssd.companyemloyee.entity.Privilege;

public interface PrivilegeRepositoryCustom {
	 public Optional<Privilege> findByName(String name);
	  
}
