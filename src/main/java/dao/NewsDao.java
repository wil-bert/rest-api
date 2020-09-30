package dao;

import models.News;
import java.util.List;

public interface NewsDao {

    void add(News news);

    List<News> findNewsByDepartmentId();

    void deleteById(int id);
    void clearAll();
}
