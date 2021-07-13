package com.enima.enigmaboot.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class HelloWorldController {

    //localhost:8090
    @RequestMapping("/")
    public String index(){
        return "Hello World";
    }

    //localhost:8090/customers/12
    @GetMapping("/customers/{id}")
    public String pathVar(@PathVariable String id){
        return "Customer id : " + id;
    }

    //request param / query param
    //localhost:8090/customers?page=1
    //localhost:8090/customers/page=1
    @GetMapping("/customers")
    public String reqParam(@RequestParam String page){
        return "Hasil Request Param adalah id : " + page;
    }

    @PostMapping("/reqbody")
    public String reqBody(@RequestBody HashMap<String, String> mapBody){
        return "ini hasil : " + mapBody;
    }
}
