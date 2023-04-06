package com.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {


    private UserRepository repository;

    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }

    /*@GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){

        User user = service.findOne(id);

        if(user==null){
            throw new UserNotFoundException("+id:"+id);
        }
        return user;
    }*/

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();// 201 döner created
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser (@PathVariable int id){
        repository.deleteById(id);
    }


    //Entity Model
    //WebMvcLinkBuilder
    //Hateoas
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserEntityModel(@PathVariable int id){

        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("+id:"+id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        // içine link koyduk ve bütün kullanıcılara yollamasını sağladık
        /*
        * { "id":1,
        * "name":"ADAM"
        * "birthdate":"123123-123-12,
        * "_links":{
        *       "all-users": {
        *           "href":"http:localhost.8080/users"
        *       }
        *  }
        * }
        *
        *
        *
        *
        * */
        return entityModel;


    }



}
