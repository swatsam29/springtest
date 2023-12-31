package com.example.springtest;

import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v2")
public class ValidationController {

    @GetMapping("/hello/{user-name}")
    @ResponseBody
    public String hello(@NonNull @PathVariable("user-name") String userName){
       
        int hh = Instant.now().atZone(ZoneOffset.UTC).getHour();
        String greeting = (hh > 12 && hh < 16) ? "Good Afternoon" : (hh > 15 && hh < 21) ? "Good Evening" : "Good Morning";
       
        return greeting + " " + userName.toUpperCase().trim(); 
    }


}
