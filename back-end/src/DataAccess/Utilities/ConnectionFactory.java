package DataAccess.Utilities;

import java.sql.Connection;

public class ConnectionFactory {

    //TODO: Research the connection strategy for MySQL!

    public static Connection openConnection() {
        throw new RuntimeException("Not implemented");
    }

    public static void closeConnection(boolean commit) {
        throw new RuntimeException("Not implemented");
    }
}
