package DataAccess.DAO.MySql;

import DataAccess.DAO.IDAOFactory;
import DataAccess.DAO.IFriendshipDAO;
import DataAccess.DAO.IItemDAO;
import DataAccess.DAO.IUserDAO;

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
