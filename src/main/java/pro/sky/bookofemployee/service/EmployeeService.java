package pro.sky.bookofemployee.service;

import pro.sky.bookofemployee.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String surname, String name);
    Employee remove(String surname, String name);
    Employee find(String surname, String name);

    Collection<Employee> findAll();
}
