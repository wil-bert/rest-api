//import static spark.Spark.;
//
//import com.google.gson.Gson;
//import exceptions.ApiException;
//import models.User;
//import models.Departments;
//import models.News;
//import dao.Sql2oDepartmentsDao;
//import dao.Sql2oNewsDao;
//import dao.Sql2oUsersDao;
//import org.sql2o.Sql2o;
//import org.sql2o.Connection;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class App {
//    public static void main(String[] args) {
//        Sql2oUsersDao usersDao;
//        Sql2oDepartmentsDao departmentsDao;
//        Sql2oNewsDao newsDao;
//        Connection conn;
//        Gson gson = new Gson();
//
//        staticFileLocation("/public");
//
//        usersDao = new Sql2oUsersDao(sql2o);
//        departmentsDao = new Sql2oDepartmentsDao(sql2o);
//
//
//
//        post("/user/:department/departments/:departmentId", ((request, response) -> {
//
//            String department = new String(request.params("department"));
//        }));
//    }
//}
