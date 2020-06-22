package com.manav.graphql.pack.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manav.graphql.pack.pojo.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

	Employee findByName(String argument);

}
