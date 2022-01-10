package service;

import dao.HibernatePersonDAO;
import dao.PersonDAO;
import model.Person;

import java.util.List;
import java.util.Optional;

public class PersonService {

    private PersonDAO personDao = new HibernatePersonDAO();

    public PersonService(){
    }

    public Optional<Person> findPersonById(int id) {
        return personDao.findById(id);
    }

    public void insertPerson(Person person) {
        personDao.insert(person);
    }

    public void updatePerson(int id, Person person) {
        personDao.update(id, person);
    }

    public void deletePerson(int id) {
        personDao.delete(id);
    }

    public List<Person> selectAllPersons() {
        return personDao.selectAll();
    }


}
