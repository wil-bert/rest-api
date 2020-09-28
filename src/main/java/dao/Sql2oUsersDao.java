package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUsersDao implements UsersDao {
    private final Sql2o sql2o;

    public Sql2oUsersDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (name, position, role, department) VALUES (:name, :position, :role, :department)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<User> getAll() {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);

        }
    }

    @Override
    public List<User> getAllUsersByDepartment(String department) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users WHERE department = :department")
                    .addParameter("department", department)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id= :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users";
        try (Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
