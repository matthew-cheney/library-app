package DataAccess.DAO;

public interface IDAOFactory {
    IFriendshipDAO makeFriendshipDAO();
    INodeDAO makeNodeDAO();
    IUserDAO makeUserDAO();
}
