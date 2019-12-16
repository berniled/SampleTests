package com.ssd.companyemloyee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.companyemloyee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, EmployeeRepositoryCustom {

}
