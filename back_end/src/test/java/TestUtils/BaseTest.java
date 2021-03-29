package TestUtils;

import DataAccess.Connection.ConnectionPool;

public class BaseTest {
    public static final ConnectionPool CONNECTION_POOL =
            ConnectionPool.getInstance(TestConfig.TEST_USER, TestConfig.TEST_PASSWORD);
}
