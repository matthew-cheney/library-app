package DataAccess.DAO.MySql;

import DataAccess.DAO.Interfaces.IDAOFactory;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import DataAccess.DAO.Interfaces.IItemDAO;
import DataAccess.DAO.Interfaces.IUserDAO;

public class MySqlDAOFactory implements IDAOFactory {

    @Override
    public IFriendshipDAO makeFriendshipDAO() {
        return new MySqlFriendshipDAO();
    }

    @Override
    public IItemDAO makeNodeDAO() {
        return new MySqlItemDAO();
    }

    @Override
    public IUserDAO makeUserDAO() {
        return new MySqlUserDAO();
    }
}
