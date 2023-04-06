package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
//http://localhost:8080/swagger-ui/index.html
@Entity(name="user_details")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    // http://localhost:8080/h2-console

    @Size(min=2, message = "Name should have at least 2 characters.")
   // @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth Date should be in the past.")
    //@JsonProperty("birth_date") // json da bu ad ile anılacak
    private LocalDate birthDate;


    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    protected User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
