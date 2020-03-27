package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PersonController {

    @Autowired
    PersonService service;



}
