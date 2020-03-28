package io.zipcoder.persistenceapp.repositories;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.JpaPersonService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long>{


}
