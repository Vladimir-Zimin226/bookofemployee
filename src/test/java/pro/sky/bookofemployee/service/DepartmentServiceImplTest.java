package pro.sky.bookofemployee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.bookofemployee.Employee;
import pro.sky.bookofemployee.exceptions.EmployeeNotFoundException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pro.sky.bookofemployee.EmployeeTestConstants.*;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;


    private List<Employee> employees = List.of(
            new Employee("Зимин", "Владимир", 1, 1_000_000),
            new Employee("Важин", "Евгений", 1, 750_000),
            new Employee("Лотин", "Женя", 2, 300_000));


    @Test
    void shouldFindEmployeeWithMaxSalaryByDepartmentId() {
        when(employeeService.findAll()).thenReturn(employees);
        assertEquals(employees.get(0), out.maxSalary(DEPARTMENT));
    }

    @Test
    void shouldFindEmployeeWithMinSalaryByDepartmentId() {
        when(employeeService.findAll()).thenReturn(employees);

        assertEquals(employees.get(1), out.minSalary(DEPARTMENT));
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenFindMinSalaryInEmptyEmployeeList() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> out.minSalary(DEPARTMENT));
    }

    @Test
    void shouldReturnAllEmployeeByDepartmentsWhenEmployeesExist() {
        when(employeeService.findAll()).thenReturn(employees);

        assertEquals(List.of(employees.get(0), employees.get(1)), out.getAllByDepartment(DEPARTMENT));
    }

    @Test
    void shouldReturnEmptyListWhenEmployeeDoesNotExist() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertEquals(List.of(), out.getAllByDepartment(DEPARTMENT));
    }

    @Test
    void shouldReturnAllEmployeesInMapWhenTheyExist() {
        when(employeeService.findAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                1, List.of(employees.get(0), employees.get(1)),
                2, List.of(employees.get(2)));

        assertEquals(expectedMap, out.getAll());
    }

    @Test
    void shouldReturnEmptyListWhenEmployeeIsNotFoundInMap() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Map.of(), out.getAll());

    }
}
