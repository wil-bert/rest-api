//package dao;
//
//import models.User;
//import models.Departments;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.Before;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import static org.junit.Assert.*;
//
//public class Sql2oUsersDaoTest {
//    private Connection conn;
//    private Sql2oUsersDao usersDao;
//    private Sql2oDepartmentsDao departmentsDao;
//
//    @Before
//    public void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        usersDao = new Sql2oUsersDao(sql2o);
//        departmentsDao = new Sql2oDepartmentsDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        conn.close();
//    }
//
//    @Test
//    public void addingUserSetId() throws Exception {
//        User testUser = setUpUser();
//        assertEquals(1, testUser.getId());
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        User users1 = setUpUser();
//        User users2 = setUpUser();
//        assertEquals(2, usersDao.getAll().size());
//    }
//
//    @Test
//    public void getAllUsersByDepartment() throws Exception {
//        Departments testDepartments = setUpDepartments();
//        Departments otherDepartments = setUpDepartments();
//        User users1 = setUpUserForDepartments(testDepartments);
//        User users2 = setUpUserForDepartments(testDepartments);
//        User usersForOtherDepartments = setUpUserForDepartments(otherDepartments);
//        assertEquals(2, usersDao.getAllUsersByDepartment(testDepartments.getId()).size());
//
//    }
//
//    @Test
//    public void deleteById() throws  Exception {
//        User testUser = setUpUser();
//        User otherUsers = setUpUser();
//        assertEquals(2, usersDao.getAll().size());
//        usersDao.deleteById(testUser.getId());
//        assertEquals(1, usersDao.getAll().size());
//    }
//
//    private User setUpUserForDepartments(Departments testDepartments) {
//        User user = new User("Ian Wilbert", "Employee", "Tech", "ICT");
//        usersDao.add(user);
//        return user;
//    }
//
//    private Departments setUpDepartments() {
//        Departments departments = new Departments("ICT", "Solves all technical issues about computers", 5, 1);
//        departmentsDao.add(departments);
//        return departments;
//    }
//
//    private User setUpUser() {
//        User user = new User("Ian Wilbert", "Employee", "Tech", "ICT");
//        usersDao.add(user);
//        return user;
//    }
//
//}