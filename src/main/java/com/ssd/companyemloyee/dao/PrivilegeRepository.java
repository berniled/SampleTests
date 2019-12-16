package com.ssd.companyemloyee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.companyemloyee.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer>, PrivilegeRepositoryCustom {

}
