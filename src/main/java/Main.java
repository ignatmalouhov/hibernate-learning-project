import model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.PersonService;

import javax.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Main {

    public static void main(final String[] args) {

        PersonService personService = new PersonService();
        Optional<Person> person = personService.findPersonById(3);
        System.out.println(person);

        personService.insertPerson(new Person("Jack", "Sparrow"));

        personService.deletePerson(4);

        personService.updatePerson(6, new Person("Test", "Test"));

        List<Person> persons = new ArrayList<>();
        persons = personService.selectAllPersons();

        for (Person p : persons) {
            System.out.println(p);
        }
    }
}