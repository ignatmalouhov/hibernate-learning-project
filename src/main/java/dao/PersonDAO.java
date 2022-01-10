package dao;

import model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    Optional<Person> findById(int id);

    void insert(Person person);

    void update(int id, Person person);

    void delete(int id);

    List<Person> selectAll();

}