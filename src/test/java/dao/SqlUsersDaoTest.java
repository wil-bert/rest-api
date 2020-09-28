package dao;

import models.User;
import models.Departments;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class SqlUsersDaoTest {
    private Connection conn;
    private Sql2oUsersDao usersDao;
    private Sql2oDepartmentsDao departmentDao;

}