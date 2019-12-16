package com.ssd.companyemloyee.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssd.companyemloyee.entity.Employee;

@Repository("empDaoH2")
public class EmployeeRepositoryDAO implements EmployeeRepositoryCustom {
	
	@PersistenceContext 
	private EntityManager em;

	
	@Override
	public Optional<Employee> findByName(@Param("name") String name) {
		Query query = em.createNativeQuery(
				"Select TOP 1 * FROM Employee WHERE name LIKE :name")
				.setParameter("name", name);
		List<Employee> lst = query.getResultList();
		Optional<Employee> emp = Optional.empty();
		if (lst.size() > 0) {
			emp = Optional.of(lst.get(0));
		}
		return emp;
	}

}
