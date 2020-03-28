package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service
public class JdbcPersonServiceImpl implements JpaPersonService{

    @Autowired
    PersonRepository repo;


    @Override
    public Person getPerson(Long id) {
       return repo.findOne(id);
    }

    @Override
    public void addPerson(Person person) {
        repo.save(person);
    }

    @Override
    public void updatePerson(Long id, Person person) {
        Person original = repo.findOne(id);
        original.setFirstName(person.getFirstName());
        original.setLastName(person.getLastName());
        original.setBirthDate(person.getBirthDate());
        original.setMobile(person.getMobile());
        original.setHomeId(person.getHomeId());
        repo.save(original);
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) repo.findAll();
    }

    @Override
    public List<Person> findAllWithMobile(String mobile) {
        return null;
    }

    @Override
    public List<Person> findAllWithSurName(String lastName) {
        return null;
    }

    @Override
    public List<Person> findAllWithFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Person> findAllWithBirthDay(String birthDay) {
        return null;
    }

    @Override
    public boolean deletePerson(Long id) {
        repo.delete(id);
        return true;
    }

    @Override
    public boolean deletePersonList(ArrayList<Long> ids) {
        return false;
    }

    @Override
    public Map<String, ArrayList<Person>> getMapLastNames() {
        Map<String,ArrayList<Person>> map = new HashMap<>();
        List<Person> peeps = findAll();
        peeps.forEach(p -> {
            if(map.containsKey(p.getLastName())){
                ArrayList<Person> newList = map.get(p.getLastName());
                newList.add(p);
                map.put(p.getLastName(),newList);
            } else {
                ArrayList<Person> newList = new ArrayList<>();
                newList.add(p);
                map.put(p.getLastName(),newList);
            }
        });
        return map;
    }

    @Override
    public Map<String, Integer> getMapFirstNames() {
        Map<String,Integer> map = new LinkedHashMap<>();
        List<Person> peeps = findAll();
        peeps.forEach(p -> {
            if(map.containsKey(p.getFirstName())){
                Integer newVal = map.get(p.getFirstName());
                newVal++;
                map.put(p.getFirstName(), newVal);
            } else {
                Integer val = 1;
                map.put(p.getFirstName(),val);
            }
        });
        return map;
    }
}
