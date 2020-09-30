package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    void add(NewsDao news);

    //read
    List<News> getAll();
    List<News> getAllNewsByDepartment(int departmentid);


    //update

    //delete
    void deleteById(int id);
    void clearAll();

    void setId(int id);
}
