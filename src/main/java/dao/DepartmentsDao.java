package dao;

import models.Departments;
import java.util.List;

public interface DepartmentsDao {

    void add (Departments departments);

    List<Departments> getAll();
    Departments findById(int id);

    void deleteById(int id);
    void clearAll();
}
