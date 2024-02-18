package pro.sky.bookofemployee.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.bookofemployee.Employee;
import pro.sky.bookofemployee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.bookofemployee.exceptions.EmployeeNotFoundException;
import pro.sky.bookofemployee.exceptions.InvalidInputException;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.bookofemployee.EmployeeTestConstants.*;


@SpringBootTest
class EmployeeServiceImplTest {
    private final EmployeeService out = new EmployeeServiceImpl();

    @Test
    void shouldAddEmployeeWhenTheyDontExist() {

        Employee expected = new Employee(SURNAME, NAME, DEPARTMENT, SALARY);

        assertEquals(0, out.findAll().size());
        assertFalse(out.findAll().contains(expected));

        Employee actual = out.add(SURNAME, NAME, DEPARTMENT, SALARY);

        assertEquals(expected, actual);
        assertEquals(1, out.findAll().size());
        assertTrue(out.findAll().contains(expected));

    }

    @Test
    void shouldThrowEmployeeExistsExceptionWhenTheyExist() {
        Employee existed = out.add(SURNAME, NAME, DEPARTMENT, SALARY);

        assertTrue(out.findAll().contains(existed));
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.add(SURNAME, NAME, DEPARTMENT, SALARY));
    }

    @Test
    void shouldFindEmployeeWhenTheyExist() {
        Employee existed = out.add(SURNAME, NAME, DEPARTMENT, SALARY);

        assertEquals(existed, out.find(SURNAME, NAME, DEPARTMENT, SALARY));
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeWhichDoesNotExist() {
        assertEquals(0, out.findAll().size());

        assertThrows(EmployeeNotFoundException.class, () -> out.find(SURNAME, NAME, DEPARTMENT, SALARY));
    }

    @Test
    void shouldRemoveEmployeeWhenTheyExist() {
        Employee expected = out.add(SURNAME, NAME, DEPARTMENT, SALARY);

        assertEquals(1, out.findAll().size());
        assertTrue(out.findAll().contains(expected));

        Employee actual = out.remove(SURNAME, NAME, DEPARTMENT, SALARY);

        assertEquals(expected, actual);
        assertTrue(out.findAll().isEmpty());
        assertFalse(out.findAll().contains(expected));
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenRemoveEmployeeWhichDoesNotExist() {
        assertTrue(out.findAll().isEmpty());

        assertThrows(EmployeeNotFoundException.class, () -> out.remove(SURNAME, NAME, DEPARTMENT, SALARY));
    }

    @Test
    void shouldReturnEmptyCollectionWhenEmployeesDoesNotExist() {
        assertIterableEquals(emptyList(), out.findAll());
    }

    @Test
    void shouldReturnListOfEmployeesWhenTheyExist() {
        Employee employee1 = out.add(SURNAME, NAME, DEPARTMENT, SALARY);

        Collection<Employee> expected = List.of(employee1);

        Collection<Employee> actual = out.findAll();

        assertIterableEquals(expected, actual);
    }

    @Test
    void InvalidInputExceptionTest() {
        assertThrows(InvalidInputException.class, () -> out.validateInput(SURNAME_3, NAME_3));
    }
}
