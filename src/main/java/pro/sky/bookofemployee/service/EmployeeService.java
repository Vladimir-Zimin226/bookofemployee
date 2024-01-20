package pro.sky.bookofemployee.service;

import pro.sky.bookofemployee.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String surname, String name, int departamentId, int salary);

    Employee remove(String surname, String name, int departamentId, int salary);

    Employee find(String surname, String name, int departamentId, int salary);


    Collection<Employee> findAll();

}
