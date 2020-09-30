//package models;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class UserTest {
//
//    @Before
//    public void setup() throws Exception {
//    }
//
//    @After
//    public void teardown() throws Exception {
//    }
//
//    @Test
//    public void getNameReturnsCorrectName() throws Exception{
//        User testUser = setupUser();
//        assertEquals("Ian Wilbert", testUser.getName());
//    }
//
//    @Test
//    public void getPositionReturnsCorrectPosition() throws Exception{
//        User testUser = setupUser();
//        assertEquals("Employee", testUser.getPosition());
//    }
//
//    @Test
//    public void getRoleReturnsCorrectRole() throws Exception{
//        User testUser = setupUser();
//        assertEquals("Tech", testUser.getRole());
//    }
//
//    @Test
//    public void getDepartmentReturnsCorrectDepartment() throws Exception{
//        User testUser = setupUser();
//        assertEquals("ICT", testUser.getDepartment());
//    }
//
//
//
//    private User setupUser() {
//        return new User("Ian Wilbert","Employee", "Tech", "ICT");
//    }
//
//}