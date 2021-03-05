package DataAccess.DAO.Interfaces;

public interface IDAOFactory {
    IFriendshipDAO makeFriendshipDAO();
    IItemDAO makeNodeDAO();
    IUserDAO makeUserDAO();
}
