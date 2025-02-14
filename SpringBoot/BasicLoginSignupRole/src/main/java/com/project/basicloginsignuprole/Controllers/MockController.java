package com.project.basicloginsignuprole.Controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mock")
public class MockController {

    @GetMapping("/user")
    public String userEndpoint() {
        return "Only users can access this endpoint!";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Only admins can access this endpoint!";
    }
}
