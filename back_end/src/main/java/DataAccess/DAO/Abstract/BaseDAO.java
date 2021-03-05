package DataAccess.DAO.Abstract;

import DataAccess.Connection.ConnectionPool;

public abstract class BaseDAO {
    public ConnectionPool getConnectionPool() {
        return ConnectionPool.getInstance();
    }
}
