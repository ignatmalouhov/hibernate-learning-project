package dao;

import model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtility;

import java.util.List;
import java.util.Optional;

public class HibernatePersonDAO implements PersonDAO {

    @Override
    public Optional<Person> findById(int id) {

        Person person = HibernateUtility.getSessionFactory()
                .openSession().get(Person.class, id);

        if (person != null) {
            return Optional.of(person);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void insert(Person person) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(int id, Person person) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Person tempPerson = (Person) session.get(Person.class, id);
            if (tempPerson != null) {
                tempPerson.setFirstName(person.getFirstName());
                tempPerson.setLastName(person.getLastName());
                session.update(tempPerson);
                transaction.commit();
            } else {
                throw new HibernateException("Can`t find entity for update");
            }
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Person person = (Person) session.get(Person.class, id);
            if (person != null) {
                session.delete(person);
                transaction.commit();
            } else {
                throw new HibernateException("Can`t delete entity");
            }
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Person> selectAll() {
        List<Person> persons = (List<Person>) HibernateUtility.getSessionFactory()
                .openSession().createQuery("From Person").list();
        return persons;
    }
}
