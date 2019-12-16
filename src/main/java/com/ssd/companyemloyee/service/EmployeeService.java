package com.ssd.companyemloyee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.ssd.companyemloyee.dao.CompanyRepository;
import com.ssd.companyemloyee.dao.EmployeeRepository;
import com.ssd.companyemloyee.entity.Company;
import com.ssd.companyemloyee.entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;

	/**
	 * This will retrieve all the 
	 * @return
	 */
	public List<Employee> findAll() {
	    return empRepo.findAll();
	}
	
	public Employee findEmployeeByName(String name) {
		name = HtmlUtils.htmlEscape(name);
		Optional<Employee> comp = empRepo.findByName(name);
		return comp.orElse(new Employee());
	}
	
	/**
	 * This method will activate the company
	 * @param name
	 * @return Activated company
	 */
	public Employee activateEmployee(String name) {
		Employee emp = findEmployeeByName(name);
		emp.setActive(true);
		return empRepo.save(emp);
	}
	
	/**
	 * This method will deactivate the company
	 * @param name
	 * @return Deactivated company
	 */
	public Employee deactivateEmployee(String name) {
		Employee emp = findEmployeeByName(name);
		emp.setActive(false);
		return empRepo.save(emp);
	}
	
	/**
	 * This method will update the company aside from the active and delete fields
	 * 
	 * @param name
	 * @return Updated company
	 */
	public Employee updateEmployee(Employee employee) {
		//set the active field to true in case 
		employee.setActive(true);
		//set the deleted field to false in case
		employee.setDeleted(false);
		return empRepo.save(employee);
	}
	
	
	/**
	 * This method will soft delete the company
	 * 
	 * @param Company comp
	 * @return Soft deleted comp
	 */
	public Employee deleteEmployee(Employee emp) {
		emp.setDeleted(true);
		return empRepo.save(emp);
	}
	
	/**
	 * This method will undelete the employee
	 * 
	 * @param Employee emp
	 * @return Undelete emp
	 */
	public Employee undeleteEmployee(Employee emp) {
		emp.setDeleted(false);
		return empRepo.save(emp);
	}
	
	/**
	 * This will create a new employee
	 */
	public Employee createEmployee(Employee emp) {
		return empRepo.save(emp);
	}
}
