package pro.sky.bookofemployee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.bookofemployee.Employee;
import pro.sky.bookofemployee.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    public final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String surname, @RequestParam String name, @RequestParam Integer departmentId, @RequestParam Integer salary) {
        return service.add(surname, name, departmentId, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String surname, @RequestParam String name, @RequestParam Integer departmentId, @RequestParam Integer salary) {
        return service.remove(surname, name, departmentId, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String surname, @RequestParam String name, @RequestParam Integer departmentId, @RequestParam Integer salary) {
        return service.find(surname, name, departmentId, salary);
    }

    @GetMapping
    public Collection<Employee> findAll(){
        return service.findAll();
    }
}
