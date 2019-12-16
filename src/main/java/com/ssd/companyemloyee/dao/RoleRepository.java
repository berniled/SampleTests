package com.ssd.companyemloyee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.companyemloyee.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>, RoleRepositoryCustom {

}
