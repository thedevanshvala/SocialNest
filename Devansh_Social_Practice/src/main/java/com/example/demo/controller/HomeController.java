package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeControllerHandler() {
        return "Devansh Vala Is Great!!!";  // This will look for 'myPage.html' in 'src/main/resources/templates'
    }
    @GetMapping("/home")
    public String homeControllerHandler2() {
        return "Devansh is Devil";  // This will look for 'myPage.html' in 'src/main/resources/templates'
    }
    @GetMapping("/Smart")
    public String homeControllerHandler3() {
        return "Devansh is Smart";  // This will look for 'myPage.html' in 'src/main/resources/templates'
    }
}
