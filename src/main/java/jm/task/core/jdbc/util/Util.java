package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection connect () {
        try {
            String url = "jdbc:mysql://localhost:3306/testdb";
            String username = "admin";
            String password = "admin";
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//    private static final SessionFactory concreteSessionFactory;
//    static {
//        try {
//            Properties prop = new Properties();
//            prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb");
//            prop.setProperty("hibernate.connection.username", "admin");
//            prop.setProperty("hibernate.connection.password", "admin");
//            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
//
//            prop.setProperty("hibernate.hbm2ddl.auto", "create");
//
//            concreteSessionFactory = new Configuration()
//                    .addProperties(prop)
//                    //.addPackage("com.kat")
//                    .addAnnotatedClass(User.class)
//                    .buildSessionFactory()
//            ;
//        }
//        catch (Exception ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//    public static Session getSession() throws HibernateException {
//        return concreteSessionFactory.openSession();
//    }
}