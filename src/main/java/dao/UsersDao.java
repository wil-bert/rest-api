package dao;

import models.Departments;
import models.User;

import java.util.List;

public interface UsersDao {

    void add(User user);

    List<User> getAll();
    List<Departments> getAllUsersByDepartment(int department);

    void deleteById(int id);
    void clearAll();
}
