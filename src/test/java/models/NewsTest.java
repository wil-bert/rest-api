package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setup() throws Exception {
    }

    @After
    public void teardown() throws Exception {
    }

    @Test
    public void getGeneralNews() {
        News testNews = setupGeneralNews();
        assertEquals("Headlines", testNews.getGeneralNews());
    }

    @Test
    public void getDepartmentNews() {
        News testNews = setupGeneralNews();
        assertEquals("Emergency", testNews.getDepartmentNews());
    }

    private News setupGeneralNews() {
        return new News("Headlines", "Emergency");
    }


}