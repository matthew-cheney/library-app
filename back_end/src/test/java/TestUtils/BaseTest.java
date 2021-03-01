package TestUtils;

import DataAccess.Connection.ConnectionPool;

public abstract class BaseTest {
    protected ConnectionPool connectionPool = ConnectionPool.getInstance(TestConfig.TEST_USER, TestConfig.TEST_PASSWORD);
}
