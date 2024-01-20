package pro.sky.bookofemployee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.bookofemployee.Employee;
import pro.sky.bookofemployee.exceptions.EmployeeAllreadyAddedException;
import pro.sky.bookofemployee.exceptions.EmployeeNotFoundException;
import pro.sky.bookofemployee.exceptions.InvalidInputException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees = new HashMap();

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String surname, String name, int departamentId, int salary) {

        validateInput(name, surname);

        Employee employee = new Employee(surname, name, departamentId, salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAllreadyAddedException("Сотрудник уже зачислен!");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String surname, String name, int departamentId, int salary) {

        validateInput(name, surname);

        Employee employee = new Employee(surname, name, departamentId, salary);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Employee find(String surname, String name, int departamentId, int salary) {

       validateInput(name, surname);

        Employee employee = new Employee(surname, name, departamentId, salary);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }


    private void validateInput(String name, String surname) {
        if (!(isAlpha(name) && isAlpha(surname))) {
            throw new InvalidInputException();
        }
    }

}


