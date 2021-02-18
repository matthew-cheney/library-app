package DataAccess.DAO;

import DataAccess.DAO.MySql.MySqlDAOFactory;

public class DAOFactorySingleton {

    private static IDAOFactory daoFactory;

    private DAOFactorySingleton() {}

    public static IDAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new MySqlDAOFactory();
        }

        return daoFactory;
    }
}
