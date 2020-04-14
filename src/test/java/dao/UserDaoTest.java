package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao;
    String username,pw;
    String username2,pw2;
    @Before
    public void setUp() throws Exception {
        userDao=new UserDao();
        username="admin";
        pw="123456";
        username2="admin";
        pw2="123";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void check() {
        assertEquals(true,userDao.check(username,pw));
        assertEquals(false,userDao.check(username2,pw2));
    }
}