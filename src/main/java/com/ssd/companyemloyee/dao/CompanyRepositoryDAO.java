package com.ssd.companyemloyee.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssd.companyemloyee.entity.Company;

@Repository("companyDaoH2")
public class CompanyRepositoryDAO implements CompanyRepositoryCustom {

	@PersistenceContext 
	private EntityManager em;


	@Override
	public Optional<Company> findByName(@Param("name") String name) {
		Query query = em.createNativeQuery(
				"Select TOP 1 * FROM Company WHERE name = :name")
				.setParameter("name", name);
		List<Company> lst = query.getResultList();
		Optional<Company> comp = Optional.empty();
		if (lst.size() > 0) {
			comp = Optional.of(lst.get(0));
		}
		return comp;
	}



}
