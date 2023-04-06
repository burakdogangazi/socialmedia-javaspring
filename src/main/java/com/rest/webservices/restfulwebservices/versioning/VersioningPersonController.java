package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //URI Versioning -twitter
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Bob","Charlie"));
    }


    //Param Versioning- amazon
    @GetMapping(path="/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path="/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    //Custom headers versioning - microsoft

    @GetMapping(path="/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeader(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path="/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeader(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    //Media Type Versioning

    @GetMapping(path="/person/accept", produces = "appliaction/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAccept(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path="/person/accept", produces = "appliaction/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAccept(){
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

