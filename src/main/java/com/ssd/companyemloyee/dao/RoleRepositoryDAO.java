package com.ssd.companyemloyee.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssd.companyemloyee.entity.Role;

@Repository("roleDaoH2")
public class RoleRepositoryDAO implements RoleRepositoryCustom{

	
	@PersistenceContext 
	private EntityManager em;


	@Override
	public Optional<Role> findByName(@Param("name") String name) {
		javax.persistence.Query query = em.createNativeQuery(
				"Select TOP 1 * FROM Role WHERE name = :name")
				.setParameter("name", name);
		List<Role> lst = query.getResultList();
		Optional<Role> role = Optional.empty();
		if (lst.size() > 0) {
			role = Optional.of(lst.get(0));
		}
		return role;
	}

}
