package com.ssd.companyemloyee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.companyemloyee.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
