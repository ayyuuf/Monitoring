package backend.sitemanagment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CobaContorller {
    @GetMapping("/")
    public String getMessage() {
        return "Hello from Docker!";
    }
}
