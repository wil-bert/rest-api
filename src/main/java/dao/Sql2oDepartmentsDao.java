package dao;

import models.Departments;
import models.User;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.ArrayList;

import java.util.List;

public class Sql2oDepartmentsDao implements DepartmentsDao{
    private final Sql2o sql2o;

    public Sql2oDepartmentsDao(Sql2o sql2o) {
        this.sql2o = sql2o;

    }

    @Override
    public void add(Departments departments) {
        String sql = "INSERT INTO department (departmentName, description, numberOfEmployees, departmentId) VALUES (:departmentname, :description, :numberofemployees, :departmentid)";
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
    public void addDepartmentToUser(Departments department, User user) {
        String sql = "INSERT INTO departments_users (departmentId, UserId) VALUES (:departmentid, :userid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("department", user.getDepartment())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Departments> getAll() {
        try (Connection con = sql2o.open()) {
            System.out.println(con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Departments.class));
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Departments.class);
        }
    }

    @Override
    public List<User> getAllUsersByDepartment(int departmentId) {
        ArrayList<User> users = new ArrayList<>();

        String joinQuery = "SELECT userid FROM departments_users WHERE departmentid = :departmentid";

        try (Connection con = sql2o.open()) {
            List<Integer> allUsersIds = con.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer userId : allUsersIds){
                String userQuery = "SELECT * FROM users WHERE id = :userid";
                users.add(
                        con.createQuery(userQuery)
                                .addParameter("userid", userId)
                                .executeAndFetchFirst(User.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return users;
    }

    @Override
    public Departments findById(int departmentId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments WHERE departmentId = :departmentid")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetchFirst(Departments.class);
        }
    }

    @Override
    public void update(int id, String departmentName, String description, int numberOfEmployees, int departmentId) {
        String sql = "UPDATE departments SET (id, departmentName, description, numberOfEmployees, departmentId)=(:id, :departmentName, :description, :numberofemployees, :departmentid) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("departmentName", departmentName)
                    .addParameter("description", description)
                    .addParameter("numberOfEmployees", numberOfEmployees)
                    .addParameter("departmentId", departmentId)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String deleteJoin = "DELETE from departments_user WHERE departmentId = :departmentid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("departmentId", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}

