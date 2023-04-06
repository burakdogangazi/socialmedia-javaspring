package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //URI Versioning
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
}

/**
 *
 * URLs
 *
 * URI Versioning
 *
 * V1: http://localhost:8080/v1/person
 * @GetMapping("/v1/person")
 * V2: http://localhost:8080/v2/person
 * @GetMapping("/v2/person")
 * Request Param Versioning
 *
 * V1: http://localhost:8080/person?version=1
 * @GetMapping(path = "/person", params = "version=1")
 * V2: http://localhost:8080/person?version=2
 * @GetMapping(path = "/person", params = "version=2")
 *
 *
 *
 */

