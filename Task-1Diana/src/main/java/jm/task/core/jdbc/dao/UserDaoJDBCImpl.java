package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                                    "id BIGSERIAL PRIMARY KEY NOT NULL," +
                                    "name VARCHAR(255) NOT NULL," +
                                    "last_name VARCHAR(255) NOT NULL," +
                                    "age INT)");
            System.out.println("Тфблица создана");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("Поэт с именем " + name + " " + lastName + " добавлен(а)");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Поэт с id №" + id + " был удалён(а)");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("TRUNCATE TABLE users")) {
            ps.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
