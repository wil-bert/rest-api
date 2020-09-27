package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setup() throws Exception {
    }

    @After
    public void teardown() throws Exception {
    }

    @Test
    public void getNameReturnsCorrectName() throws Exception{
        User testUser = setupUser();
        assertEquals("Ian Wilbert", testUser.getName());
    }

    private User setupUser() {
        return new User("Ian Wilbert","Employee", "Tech", "ICT");
    }

}