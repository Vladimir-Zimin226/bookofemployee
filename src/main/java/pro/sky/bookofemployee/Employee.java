package pro.sky.bookofemployee;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.*;

public class Employee {

    private static int count;
    private final String surname;
    private final String name;

    private int departament;
    private int salary;

    public int getDepartament() {
        return departament;
    }

    public void setDepartament(int departament) {
        this.departament = departament;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return name + " " + surname;
    }


    public Employee(String surname, String name, int departament, int salary) {
        this.surname = capitalize(surname.toLowerCase());
        this.name = capitalize(name.toLowerCase());;
        this.departament = departament;
        this.salary = salary;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }


    public static int getCount() {
        return count;
    }


    @Override
    public String toString() {
        return "Сотрудник " + " - " +
                "Фамилия: " + surname +
                " Имя: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(surname, employee.surname) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name);
    }


    private String validateSurname(String surname) {
        return surname == null ? "WithoutSurname" : surname;
    }

    private String validateName(String name) {
        return name == null ? "WithoutName" : name;
    }

}
