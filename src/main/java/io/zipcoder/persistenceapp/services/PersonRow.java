package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRow implements RowMapper<Person> {


        @Override
        public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setFirstName(resultSet.getString("first_Name"));
            person.setLastName(resultSet.getString("last_Name"));
            person.setMobile(resultSet.getString("mobile"));
            person.setBirthDate(resultSet.getString("birthday"));
            person.setHomeId(resultSet.getInt("home_Id"));
            return person;
        }

}
