package com.uplaodFile.theamleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uplaodFile.theamleaf.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
 