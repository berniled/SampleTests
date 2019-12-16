package com.ssd.companyemloyee.dao;

import java.util.Optional;

import com.ssd.companyemloyee.entity.Employee;

public interface EmployeeRepositoryCustom {
	public Optional<Employee> findByName(String name);
	 
}
