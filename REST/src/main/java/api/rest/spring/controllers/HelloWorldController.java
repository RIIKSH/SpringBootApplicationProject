package api.rest.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/greetings")
    public String getHelloWorld() {
        return "Hello World";
    }
}
