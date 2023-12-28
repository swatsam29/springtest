package com.example.springtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;



@Controller
public class validationController {
    @GetMapping("/hello")
    public String hello(Model model){

        model.addAttribute("message", "Hello World!!"); 
        return "Welcome"; 

    }
}
