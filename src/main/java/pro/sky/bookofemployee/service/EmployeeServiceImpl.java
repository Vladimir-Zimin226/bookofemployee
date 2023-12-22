package pro.sky.bookofemployee.service;

import org.springframework.stereotype.Service;
import pro.sky.bookofemployee.Employee;
import pro.sky.bookofemployee.exceptions.EmployeeAllreadyAddedException;
import pro.sky.bookofemployee.exceptions.EmployeeNotFoundException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees = new HashMap();

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String surname, String name) {
        Employee employee = new Employee(surname, name);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAllreadyAddedException("Сотрудник уже зачислен!");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String surname, String name) {
        Employee employee = new Employee(surname, name);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Employee find(String surname, String name) {
        Employee employee = new Employee(surname, name);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
