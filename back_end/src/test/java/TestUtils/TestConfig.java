package TestUtils;

import DataAccess.Connection.ConnectionPool;

public class TestConfig {

    private static final String TEST_USER = "tadhgcra_loptest";
    private static final String TEST_PASSWORD = "qCxQAMNV8ghbBa9";

    public static final ConnectionPool CONNECTION_POOL =
            ConnectionPool.getInstance(TestConfig.TEST_USER, TestConfig.TEST_PASSWORD);

    public static final int TEST_OFFSET = 0;
}
