package DataAccess.DAO.MySql;

import DataAccess.DAO.IDAOFactory;
import DataAccess.DAO.IFriendshipDAO;
import DataAccess.DAO.INodeDAO;
import DataAccess.DAO.IUserDAO;

public class MySqlDAOFactory implements IDAOFactory {

    @Override
    public IFriendshipDAO makeFriendshipDAO() {
        return new MySqlFriendshipDAO();
    }

    @Override
    public INodeDAO makeNodeDAO() {
        return new MySqlNodeDAO();
    }

    @Override
    public IUserDAO makeUserDAO() {
        return new MySqlUserDAO();
    }
}
