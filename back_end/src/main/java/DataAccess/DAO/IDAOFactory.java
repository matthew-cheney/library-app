package DataAccess.DAO;

public interface IDAOFactory {
    IFriendshipDAO makeFriendshipDAO();
    IItemDAO makeNodeDAO();
    IUserDAO makeUserDAO();
}
