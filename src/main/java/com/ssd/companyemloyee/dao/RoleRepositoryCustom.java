package com.ssd.companyemloyee.dao;

import java.util.Optional;


import com.ssd.companyemloyee.entity.Role;

public interface RoleRepositoryCustom {
	public Optional<Role> findByName(String name);
	  
}
