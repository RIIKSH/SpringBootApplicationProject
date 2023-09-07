package api.rest.spring.controllers;

import api.rest.spring.models.Employee;
import api.rest.spring.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> postEmployee(@RequestBody @Valid Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public Employee putEmployee(@Valid @RequestBody Employee employee)  {
        return employeeService.updateEmployee(employee);
    }

    @PatchMapping("/employees")
    public Employee patchEmployee(@Valid @RequestBody Employee employee)  {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee + " + id + " Deleted Successfully", HttpStatus.NO_CONTENT);
    }
}
