package com.shree.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class Democontroller {
    @GetMapping("/abc")
    public String MethodName()
    {
        return "Hello World, Spring Boot";
    }
    //http://localhost:8080/abc

    @GetMapping("/home")
    public String home() {
        return "<h1>Home Page</h1>";
    }
    //http://localhost:8080/home

    @GetMapping("/Avg")
	public float GetAvg(@RequestParam float n1,@RequestParam float n2,@RequestParam float n3)
	{
		float a =(n1+n2+n3)/3;
		return a;
	}
    //http://localhost:8080/Avg?n1=3&n2=4&n3=3

    @GetMapping("/Id")
    public String GetName(@RequestParam String Name,@RequestParam int Id) {
        return ("Good Morning "+Name+" "+Id);
    }
    //http://localhost:8080/Id?Name=Shree&Id=49

    @GetMapping("/{Std_Name}")
    public String getName(@PathVariable("Std_Name") String name)
	{
		return "Good Morning "+name;
	}
    //http://localhost:8080/shree
    
    

}
