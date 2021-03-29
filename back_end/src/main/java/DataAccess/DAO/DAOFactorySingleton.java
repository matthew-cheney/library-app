package DataAccess.DAO;

import DataAccess.DAO.Interfaces.IDAOFactory;
import DataAccess.DAO.MySql.MySqlDAOFactory;

public class DAOFactorySingleton {

    private static DataAccess.DAO.Interfaces.IDAOFactory daoFactory;

    private DAOFactorySingleton() {}

    public static IDAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new MySqlDAOFactory();
        }

        return daoFactory;
    }
}
