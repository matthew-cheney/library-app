package DataAccess.Connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionFactoryTest {

    @Test
    public void openAndCloseConnection_Success() {
        Connection connection = ConnectionFactory.openConnection();
        assertNotNull(connection);
        ConnectionFactory.closeConnection(false);
        String errorReceived = "";
        try {

            connection.prepareStatement("Bogus SQL Command");
        }
        catch (SQLException ex) {
            errorReceived = ex.getMessage();
        }
        assertEquals("No operations allowed after connection closed.", errorReceived);
    }
}
