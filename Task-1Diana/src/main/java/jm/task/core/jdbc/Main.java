package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    private static UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userDaoJDBC.dropUsersTable();

        userDaoJDBC.createUsersTable();


        userDaoJDBC.saveUser("Alexander", "Pushkin", (byte) 37);
        userDaoJDBC.saveUser("Nicolai", "Gogol", (byte) 42);
        userDaoJDBC.saveUser("Lev", "Tolstoi", (byte) 82);
        userDaoJDBC.saveUser("Marina", "Tsvetaeva", (byte) 48);

        userDaoJDBC.getAllUsers().forEach(System.out::println);

        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.getAllUsers().forEach(System.out::println);

        userDaoJDBC.dropUsersTable();

    }
}
