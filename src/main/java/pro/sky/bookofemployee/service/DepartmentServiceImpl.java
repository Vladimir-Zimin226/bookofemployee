package pro.sky.bookofemployee.service;

import org.springframework.stereotype.Service;
import pro.sky.bookofemployee.Employee;
import pro.sky.bookofemployee.exceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl service;

    public DepartmentServiceImpl(EmployeeServiceImpl service) {
        this.service = service;
    }

    @Override
    public List<Employee> getAllByDepartment(int department) {
        return service.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        return service.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Employee maxSalary(int department) {
        return service.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);

    }

    @Override
    public Employee minSalary(int department) {
        return service.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);

    }

    @Override
    public int sumSalary(int department) {
        return service.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }
}
