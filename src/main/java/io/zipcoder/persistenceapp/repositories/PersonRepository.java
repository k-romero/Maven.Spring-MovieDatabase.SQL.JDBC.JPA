package io.zipcoder.persistenceapp.repositories;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.JpaPersonService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long>{

    Iterable<Person> findAllByBirthDate(String birthDate);
    Iterable<Person> findAllByLastName(String lastName);
    Iterable<Person> findAllByFirstName(String lastName);
    Iterable<Person> findAllByMobile(String mobile);


}
