package pro.sky.bookofemployee;

import java.util.Objects;

public class Employee {

    private static int count;
    private final String surname;
    private final String name;


    public Employee(String surname, String name) {
        this.surname = surname;
        this.name = name;
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
