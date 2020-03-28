package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.JdbcPersonServiceImpl;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;


@RestController
public class PersonController {

    @Autowired
    JdbcPersonServiceImpl service;

    public PersonController(JdbcPersonServiceImpl service){
        this.service = service;
    }

    //For JDBC PERSON SERVICE
//    @Autowired
//    PersonService service;
//    public PersonController(PersonService service) {
//        this.service = service;
//    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        return new ResponseEntity<>(service.getPerson(id),HttpStatus.OK);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<?> persistPerson(@RequestBody Person p){
        service.addPerson(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/person/{id}")
    public void updatePerson(@RequestBody Person person, @PathVariable Long id) {
        service.updatePerson(id,person);
    }

    @GetMapping("/person/")
    public ResponseEntity<Iterable<Person>> getAllPeople(){
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }

    @GetMapping("/person/mobile/{mobile}")
    public ResponseEntity<Iterable<Person>> getAllPeopleMobile(@PathVariable String mobile){
        return new ResponseEntity<>(service.findAllWithMobile(mobile),HttpStatus.OK);
    }

    @GetMapping("/person/lastName/{lastName}")
    public ResponseEntity<Iterable<Person>> getAllPeopleSurName(@PathVariable String lastName){
        return new ResponseEntity<>(service.findAllWithSurName(lastName),HttpStatus.OK);
    }

    @GetMapping("/person/firstName/{firstName}")
    public ResponseEntity<Iterable<Person>> getAllPeopleFirstName(@PathVariable String firstName){
        return new ResponseEntity<>(service.findAllWithFirstName(firstName),HttpStatus.OK);
    }

    @GetMapping("/person/birthday/{birthday}")
    public ResponseEntity<Iterable<Person>> getAllPeopleBirthDay(@PathVariable String birthday){
        return new ResponseEntity<>(service.findAllWithBirthDay(birthday),HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    public boolean deletePerson(@PathVariable Long id){
        return service.deletePerson(id);
    }

    @DeleteMapping("/person/deleteList")
    public boolean deletePersonListById(@RequestBody ArrayList<Long> ids){
        return service.deletePersonList(ids);
    }

    @GetMapping("/person/lastNameMap")
    public ResponseEntity<Map<String,ArrayList<Person>>> getMappingOfLastName(){
        return new ResponseEntity<>(service.getMapLastNames(),HttpStatus.OK);
    }

    @GetMapping("/person/firstNameMap")
    public ResponseEntity<Map<String,Integer>> getMappingOfFirstName(){
        return new ResponseEntity<>(service.getMapFirstNames(),HttpStatus.OK);
    }








}
