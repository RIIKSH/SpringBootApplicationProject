package frontend.mvc.spring.controllers;

 

import frontend.mvc.spring.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

 

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

 

@Controller
public class EmployeeController {

 

    private static String BASE_URL = "http://localhost:8080/api/";

 

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(path = "/greet")
    public String greetings() {
        return "HelloWorld";
    }

 

    @RequestMapping ("/employees")
    public ModelAndView home() {
        List<Employee> employees = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(BASE_URL + "employees", Employee[].class)));
        ModelAndView modelAndView = new ModelAndView("Employee");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

 

    @RequestMapping ("/getEmployee")
    public ModelAndView getEmployee() {
        List<Employee> employees = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(BASE_URL + "employees", Employee[].class)));
        ModelAndView modelAndView = new ModelAndView("Employee");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

 

    @RequestMapping("/employees/form")
    public String addEmployee() {
        return "EmployeeForm";
    }

 

    @RequestMapping (path = "/saveEmployee", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute Employee employee) {
        employee.setEmployeeId(0);
        restTemplate.postForEntity(BASE_URL + "employees", employee, Employee.class);
        return "redirect:/employees";
    }

 

    @RequestMapping(path = "/employees/form", method=RequestMethod.POST)
    public ModelAndView employeeForm(@RequestParam int id) {
        Employee employee = restTemplate.getForObject(BASE_URL + "employees/" + id, Employee.class);
        ModelAndView modelAndView = new ModelAndView("EmployeeForm");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

 

    @RequestMapping(path = "/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute Employee employee) {
        restTemplate.put(BASE_URL + "employees", employee, Employee.class);
        return "redirect:/employees";
    }

 

    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam int id) {
        restTemplate.delete(BASE_URL + "employees/" + id);
        return "redirect:/employees";
    }
}