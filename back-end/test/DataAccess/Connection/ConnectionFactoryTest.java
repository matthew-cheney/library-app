package DataAccess.Connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionFactoryTest {

    @Test
    void TestOpenConnection() {
        Connection connection = ConnectionFactory.openConnection();
    }
}
