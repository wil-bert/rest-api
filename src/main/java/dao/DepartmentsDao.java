package dao;

import models.Departments;
import models.News;
import models.User;
import java.util.List;

public interface DepartmentsDao {

    void add (Departments departments);
    void addDepartmentToUser(Departments department, User user);

    //read
    List<Departments> getAll();
    List<User> getAllUsersByDepartment(int departmentId);


    Departments  findById(int id);
    // List<user> getAllUsersForADepartment(int departmentId);

    //update
    void update(int id, String departmentName, String description, int numberOfEmployees, int departmentId);

    //delete
    void deleteById(int id);
    void clearAll();

}
