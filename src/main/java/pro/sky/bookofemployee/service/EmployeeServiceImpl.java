package pro.sky.bookofemployee.service;

import org.springframework.stereotype.Service;
import pro.sky.bookofemployee.Employee;
import pro.sky.bookofemployee.exceptions.EmployeeAllreadyAddedException;
import pro.sky.bookofemployee.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String surname, String name) {
        Employee employee = new Employee(surname, name);
        if (employeeList.add(employee)) {
            throw new EmployeeAllreadyAddedException("Сотрудник уже зачислен!");
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String surname, String name) {
        Employee employee = new Employee(surname, name);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Employee find(String surname, String name) {
        Employee employee = new Employee(surname, name);
        if (employeeList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableList(employeeList);
    }
}
