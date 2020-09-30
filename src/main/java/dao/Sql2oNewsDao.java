package dao;

import models.Departments;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {

    private final Sql2o sql2o;
    public Sql2oNewsDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(NewsDao news) {
        String sql = "INSERT INTO news (headline, content, author, departmentid, createdat, formattedCreatedAt) VALUES (:headline, :content, :author, :departmentid, :createdat, :formattedCreatedAt)"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

        }

    @Override
    public List<News> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> getAllNewsByDepartment(int departmentid) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE departmentid = :departmentid")
                    .addParameter("departmentid", departmentid)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id = :id";
        String deleteJoin = "DELETE from department_news WHERE newsid = :newsid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("news", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM news";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .executeUpdate();
        }catch (Error error){
            throw error;
        }

    }

    @Override
    public void setId(int id) {

    }


    public void addNewsToDepartment(News news, Departments departments){
    }
}