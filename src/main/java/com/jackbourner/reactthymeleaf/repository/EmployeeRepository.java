package com.jackbourner.reactthymeleaf.repository;

import com.jackbourner.reactthymeleaf.payrole.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
