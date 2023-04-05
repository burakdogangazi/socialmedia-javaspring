package com.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    //@RequestMapping(method = RequestMethod.GET, path="/hello-world")
    @GetMapping(path="/hello-world")
    public String helloworld(){
        return  "hello";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloworldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloworldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }
}
