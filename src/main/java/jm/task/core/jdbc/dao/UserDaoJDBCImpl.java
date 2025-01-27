package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            changeDB("CREATE TABLE USERSTABLE ( id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)");
            System.out.println("Создана таблица USERSTABLE");
        } catch (Exception e) {
            System.out.println("Не удалось создать (возможно база уже существует)");
        }
    }

    public void dropUsersTable() {
        try {
            changeDB("DROP TABLE USERSTABLE");
            System.out.println("Удалена таблица USERSTABLE");
        } catch (Exception e) {
            System.out.println("Не удалось удалить (возможно базы уже не существует)");
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERSTABLE (name, lastName, age) VALUES (?, ?, ?)";
        Connection connection = Util.connect();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User c именем - "+ name + " добавлен в базу");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка записи. Таблицы не существует.");
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            changeDB("DELETE FROM USERSTABLE WHERE id="+id);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления User( id=" + id +")" );
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        Connection connection = Util.connect();
        ResultSet rs = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM USERSTABLE");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка чтения. Таблицы не существует.");
        }
        return userList;
    }

    public void cleanUsersTable() {

        try {
            changeDB("TRUNCATE TABLE USERSTABLE");
        } catch (SQLException e) {
            System.out.println("Ошибка отчистки таблицы");;
        }

    }
    public void changeDB (String sql) throws SQLException {
        Connection connection = Util.connect();
        Statement st = null;
        st = connection.createStatement();
        st.executeUpdate(sql);
        connection.close();

    }
}
