package fr.todooz.service;

import fr.todooz.domain.Task;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jeanfeutrie
 * Date: 08/11/13
 * Time: 08:59
 * To change this template use File | Settings | File Templates.
 */
public class TaskServiceImpl implements TaskService {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public void save(Task task) {

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(task);

        transaction.commit();

        session.close();


    }

    @Override
    public void delete(Long id) {

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete Task where id = :id").setLong("id", id).executeUpdate();


        transaction.commit();

        session.close();

    }

    @Override
    public List<Task> findAll() {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Task.class);

        List<Task> tasks = criteria.list();

        session.close();

        return tasks;
    }

    @Override
    public List<Task> findByQuery(String query) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Task.class).add(Restrictions.ilike("title", query, MatchMode.ANYWHERE));

        List<Task> tasks=criteria.list();

        session.close();
        return tasks;
    }

    @Override
    public int count() {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Task.class);

        int count = this.findAll().size();


        session.close();
        return count;
    }




}
