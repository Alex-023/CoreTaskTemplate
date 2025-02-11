package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE USERSTABLE ( id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (Exception e){
            System.out.println("Ошибка создания таблицы. Возможно таблица уже создана");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("----------------------------------------------------------------------------");
        } finally {
            closeSession(session);
        }


    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS USERSTABLE");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена.");
        } catch (Exception e) {
            System.out.println("Ошибка удаления таблицы");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("----------------------------------------------------------------------------");
        } finally {
            closeSession(session);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        try {
            session = Util.getSession();
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            System.out.println("User c именем - "+ name + " добавлен в базу");
        } catch (Exception e){
            System.out.println("Ошибка сохранения!");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("----------------------------------------------------------------------------");
        } finally {
            closeSession(session);
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления!");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("----------------------------------------------------------------------------");
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction = null;
        List<User> users = new ArrayList<>();

        try {
            session = Util.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            users = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка получения списка!");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("----------------------------------------------------------------------------");
            users = new ArrayList<>();
        } finally {
            closeSession(session);
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSession();
            transaction = session.beginTransaction();
            String hql = String.format("delete from User");
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка очистки таблицы");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("----------------------------------------------------------------------------");
        } finally {
            closeSession(session);
        }
    }
}
