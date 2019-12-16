package com.ssd.companyemloyee.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssd.companyemloyee.entity.Privilege;


@Repository("privilegeDaoH2")
public class PrivilegeRepositoryDAO implements PrivilegeRepositoryCustom {
	@PersistenceContext 
	private EntityManager em;


	@Override
	public Optional<Privilege> findByName(@Param("name") String name) {
		javax.persistence.Query query = em.createNativeQuery(
				"Select TOP 1 * FROM Privilege WHERE name = :name")
				.setParameter("name", name);
		List<Privilege> lst = query.getResultList();
		Optional<Privilege> priv = Optional.empty();
		if (lst.size() > 0) {
			priv = Optional.of(lst.get(0));
		}
		return priv;
	}
}
