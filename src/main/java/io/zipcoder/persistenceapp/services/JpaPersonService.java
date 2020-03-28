package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;

import java.util.*;

public interface JpaPersonService {

    public Person getPerson(Long id);

    public void addPerson(Person person);

    public void updatePerson(Long id, Person person);

    public List<Person> findAll();

    public List<Person> findAllWithMobile(String mobile);

    public List<Person> findAllWithSurName(String lastName);

    public List<Person> findAllWithFirstName(String firstName);

    public List<Person> findAllWithBirthDay(String birthDay);

    public boolean deletePerson(Long id);

    public boolean deletePersonList(ArrayList<Long> ids);

    public Map<String,ArrayList<Person>> getMapLastNames();

    public Map<String,Integer> getMapFirstNames();
}
