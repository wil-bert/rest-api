import dao.Sql2oDepartmentsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Sql2oUsersDao usersDao;
        Sql2oDepartmentsDao departmentsDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();
    }
}
