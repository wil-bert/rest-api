import com.google.gson.Gson;
import dao.Sql2oDepartmentsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import exceptions.ApiException;
import models.Departments;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        Sql2oNewsDao sql2oNewsDao;
        Sql2oUsersDao sql2oUsersDao;
        Sql2oDepartmentsDao sql2oDepartmentsDao;
        Connection conn;
        Gson gson = new Gson();
        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        sql2oDepartmentsDao=new Sql2oDepartmentsDao(sql2o);
        sql2oNewsDao=new Sql2oNewsDao(sql2o);
        sql2oUsersDao=new Sql2oUsersDao(sql2o);
        conn=sql2o.open();

        //adding a new user
        post("/users/new","application/json",(request, response) -> {
            User user=gson.fromJson(request.body(),User.class);
            sql2oUsersDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });
        post("/departments/new","application/json",(request, response) -> {
            Departments departments =gson.fromJson(request.body(),Departments.class);
            sql2oDepartmentsDao.add(departments);
            response.status(201);
            return gson.toJson(departments);
        });
        //adding users to a specific department
        post("/add/user/:user_id/department/:department_id","application/json",(request, response) -> {

            int userId=Integer.parseInt(request.params("userId"));
            int departmentId=Integer.parseInt(request.params("departmentId"));
            Departments departments=sql2oDepartmentsDao.findById(int departmentId);
            User users=sql2oUsersDao.getAllUsersByDepartment(int departmentId);
            if(departments==null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("department_id")));
            }
            if(users==null){
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("user_id")));
            }
            sql2oDepartmentsDao.getAllUsersByDepartment(users, departments);

            List<User> departmentUsers=sql2oDepartmentsDao.getAllUsersInDepartment(departments.getId());

            response.status(201);
            return gson.toJson(departmentUsers);
        });

        post("/news/new/department","application/json",(request, response) -> {
            News department_news =gson.fromJson(request.body(),News.class);
            Departments departments=sql2oDepartmentsDao.findById(department_news.getDepartment_id());
            Users users=sql2oUsersDao.findById(department_news.getUser_id());
            if(departments==null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            if(users==null){
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")));
            }
            sql2oNewsDao.addNews(department_news);
            response.status(201);
            return gson.toJson(department_news);
        });

        post("/news/new/general","application/json",(request, response) -> {//tested

            News news =gson.fromJson(request.body(),News.class);
            sql2oNewsDao.addNews(news);
            response.status(201);
            return gson.toJson(news);
        });


        //getting users in the department

        get("/users", "application/json", (request, response) -> {//tested

            if(sql2oDepartmentsDao.getAll().size() > 0){
                return gson.toJson(sql2oUsersDao.getAll());
            }
            else {
                return "{\"RESPONSE\":\"NO USERS CURRENTLY\"}";
            }
        });

        //path to show departments
        get("/departments","application/json",(request, response) -> {//tested.............
            if(sql2oDepartmentsDao.getAll().size()>0){
                return gson.toJson(sql2oDepartmentsDao.getAll());
            }
            else {
                return "{\"RESPONSE\":\"NO DEPARTMENTS CURRENTLY\"}";
            }
        });
        //path to get listed general news
        get("/news/general","application/json",(request, response) -> {//tested.....works!!
            if(sql2oNewsDao.getAll().size()>0){
                return gson.toJson(sql2oNewsDao.getAll());
            }
            else {
                return "{\"RESPONSE\":\"NO NEWS AVAILABLE\"}";
            }
        });
//        //path to get each user department
        get("/user/:id/departments","application/json",(request, response) -> {//tested ......works
            int id=Integer.parseInt(request.params("id"));
            if(sql2oUsersDao.getAllUserDepartments(id).size()>0){
                return gson.toJson(sql2oUsersDao.getAllUserDepartments(id));
            }
            else {
                return "{\"RESPONSE\":\"DEPARTMENT NOT AVAILABLE\"}";
            }
        });
        //getting specific user..................
        get("/user/:id", "application/json", (request, response) -> {//tested and works...........
            int id=Integer.parseInt(request.params("id"));
            if(sql2oUsersDao.findById(id)==null){
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")));
            }
            else {
                return gson.toJson(sql2oUsersDao.findById(id));
            }
        });
        get("/department/:id/users","application/json",(request, response) -> {//tested works............
            int id=Integer.parseInt(request.params("id"));
            if(sql2oDepartmentsDao.getAllUsersInDepartment(id).size()>0){
                return gson.toJson(sql2oDepartmentsDao.getAllUsersInDepartment(id));
            }
            else {
                return "{\"message\":\"I'm sorry, but department has no users.\"}";
            }
        });
        get("/department/:id","application/json",(request, response) -> {//works.......
            int id=Integer.parseInt(request.params("id"));
            if(sql2oDepartmentsDao.findById(id)==null){
                throw new ApiException(404, String.format("No department with the id: %s exists",
                        request.params("id")));
            }
            else {
                return gson.toJson(sql2oDepartmentsDao.findById(id));
            }
        });
        get("/news/department/:id","application/json",(request, response) -> {//works

            int id=Integer.parseInt(request.params("id"));
            Departments departments=sql2oDepartmentsDao.findById(id);
            if(departments==null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            if(sql2oDepartmentsDao.getDepartmentNews(id).size()>0){
                return gson.toJson(sql2oDepartmentsDao.getDepartmentNews(id));
            }
            else {
                return "{\"RESPONSE\":\"THIS DEPARTMENT HAS NO NEWS CURRENTLY\"}";
            }
        });


        //FILTERS
        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });


        after((request, response) ->{
            response.type("application/json");
        });



    }
}