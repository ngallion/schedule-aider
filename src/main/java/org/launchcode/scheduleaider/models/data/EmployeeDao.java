package org.launchcode.scheduleaider.models.data;

import org.launchcode.scheduleaider.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Integer> {



}
