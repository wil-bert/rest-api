package dao;

import models.User;

import java.util.List;

public interface UsersDao {

    void add(User user);

    List<User> getAll();
    List<User> getAllUsersByDepartment(int department);

    void deleteById(int id);
    void clearAll();
}
