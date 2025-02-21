package com.shree.springapp.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class response {
    @GetMapping("/jsonproof")
    public message abc()
    {
        message m=new message(1,"Deepika","Bollineni");
        return m;
    }
    //http://localhost:8080/jsonproof

}
