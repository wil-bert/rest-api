package dao;

import models.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentsDao implements DepartmentsDao{
    private final Sql2o sql2o;

    public Sql2oDepartmentsDao(Sql2o sql2o) {
        this.sql2o = sql2o;

    }

    @Override
    public void add(Departments departments) {
        String sql = "INSERT INTO department (departmentname, description, numberofemployees, departmentId) VALUES (:departmentname, :description, :numberofemployees, :departmentid)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(departments)
                    .executeUpdate()
                    .getKey();
            departments.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Departments> getAll() {
        return null;
    }

    @Override
    public Departments findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}

