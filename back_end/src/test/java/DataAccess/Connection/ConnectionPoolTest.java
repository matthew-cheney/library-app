package DataAccess.Connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionPoolTest {

    @Test
    public void openAndCloseConnection_Success() {
        Connection connection = TestConfig.CONNECTION_POOL.getConnection();
        assertNotNull(connection);
        TestConfig.CONNECTION_POOL.freeConnection(connection, false);
        String errorReceived = "";
        try {

            connection.prepareStatement("Bogus SQL Command");
        }
        catch (SQLException ex) {
            errorReceived = ex.getMessage();
        }
        assertNotNull(errorReceived);
        assertTrue(errorReceived.contains("You can't operate on a closed Connection!!!"));
    }
}
