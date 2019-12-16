package com.ssd.companyemloyee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.companyemloyee.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {

}
