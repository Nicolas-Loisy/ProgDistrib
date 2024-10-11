package com.example.essai;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Demo {
    @GetMapping("car")
    public String getMethodName() {
        return new String("Hello ");
    }

}
