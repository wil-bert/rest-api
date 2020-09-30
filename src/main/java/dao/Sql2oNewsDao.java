package dao;

import models.Departments;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsDao implements NewsDao {

    private final Sql2o sql2o;
    public Sql2oNewsDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (type, title, content, departmentId) VALUES (:type, :title, :content, :departmentId);"; //if you change your model, be sure to update here as well!
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
    public List<News> findNewsByDepartmentId() {
        ArrayList<News> news = new ArrayList<>();
        String joinQuery = "SELECT newsid FROM department_news WHERE departmentid = :departmentid";

        try (Connection con = sql2o.open()) {
            List<Integer> allNewsIds = con.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer eachId : allNewsIds){
                String newsQuery = "SELECT * FROM news WHERE id = :eachId";
                news.add(
                        con.createQuery(newsQuery)
                                .addParameter("eachId", eachId)
                                .executeAndFetchFirst(News.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return news;
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


    public void addNewsToDepartment(News news, Departments departments){
    }
}