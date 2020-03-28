package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class PersonService {

    DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:testdb","sa","");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


    public Person getPerson(Long id){
       Person person = jdbcTemplate.queryForObject("SELECT * FROM PERSON WHERE ID = ?;", new Object[]{id}, new PersonRow());
        return person;
    }

    public void addPerson(Person person){
        Long id = person.getId();
        String fN = person.getFirstName();
        String ln = person.getLastName();
        String bD = person.getBirthDate();
        String mB = person.getMobile();
        int hId = person.getHomeId();
        this.jdbcTemplate.execute("INSERT INTO PERSON (id, FIRST_NAME, LAST_NAME, BIRTHDAY, mobile, HOME_ID)"
                + " VALUES (" + id + ",'" + fN + "','" + ln + "','" + bD + "','" + mB + "'," +hId + ")");
    }

    public void updatePerson(Long id, Person person){
        String fN = person.getFirstName();
        String ln = person.getLastName();
        String bD = person.getBirthDate();
        String mB = person.getMobile();
        int hId = person.getHomeId();
        this.jdbcTemplate.execute("UPDATE PERSON SET FIRST_NAME = '" + fN + "', LAST_NAME = '" + ln + "', BIRTHDAY = '" + bD + "', MOBILE = '" + mB + "', HOME_ID = " + hId + "WHERE ID = " + id);
    }

    public List<Person> findAll(){
        return jdbcTemplate.query("SELECT * FROM PERSON",new PersonRow());
    }

    public List<Person> findAllWithMobile(String mobile){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE mobile = + '" + mobile + "';",new PersonRow());
    }

    public List<Person> findAllWithSurName(String lastName){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE LAST_NAME = + '" + lastName + "';",new PersonRow());
    }

    public List<Person> findAllWithFirstName(String firstName){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE FIRST_NAME = + '" + firstName + "';",new PersonRow());
    }

    public List<Person> findAllWithBirthDay(String birthDay){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE BIRTHDAY = + '" + birthDay + "';",new PersonRow());
    }

    public boolean deletePerson(Long id){
        jdbcTemplate.execute("DELETE FROM PERSON WHERE ID =" + id);
        return true;
    }

    public boolean deletePersonList(ArrayList<Long> ids){
        ids.stream().forEach(i -> jdbcTemplate.execute("DELETE FROM PERSON WHERE ID =" + i));
        return true;
    }

    public Map<String,ArrayList<Person>> getMapLastNames(){
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

    public Map<String,Integer> getMapFirstNames(){
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
