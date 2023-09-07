package api.rest.spring.exceptions;

public class EmployeeNotFoundException extends RuntimeException {


    public EmployeeNotFoundException(int id) {
        super("Employee " + id + " Not Found");
    }
}
