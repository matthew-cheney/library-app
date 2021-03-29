package DataAccess.DAO.Interfaces;

public interface IDAOFactory {
    IFriendshipDAO makeFriendshipDAO();
    IItemDAO makeItemDAO();
    IUserDAO makeUserDAO();
}
