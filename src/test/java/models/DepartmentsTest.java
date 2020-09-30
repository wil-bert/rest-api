//package models;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class DepartmentsTest {
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
//    public void getDepartmentNameReturnsDepartmentName() throws Exception {
//        Departments testDepartments = setupDepartments();
//        assertEquals("ICT", testDepartments.getDepartmentName());
//    }
//
//    @Test
//    public void getDescriptionReturnsDescription() throws Exception {
//        Departments testDepartments = setupDepartments();
//        assertEquals("Solves all technical issues about computers", testDepartments.getDescription());
//    }
//
//    @Test
//    public void getNumberOfEmployeesReturnsNumberOfEmployees() throws Exception {
//        Departments testDepartments = setupDepartments();
//        assertEquals( 5, testDepartments.getNumberOfEmployees());
//    }
//
//    @Test
//    public void getDepartmentIdReturnsDepartmentId() throws Exception {
//        Departments testDepartments = setupDepartments();
//        assertEquals(1, testDepartments.getDepartmentId());
//    }
//
//    private Departments setupDepartments() {
//        return new Departments("ICT", "Solves all technical issues about computers", 5, 1);
//    }
//
//}