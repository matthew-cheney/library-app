package DataAccess.DAO;

import DataAccess.Entities.Node;

public interface INodeDAO {
    Node getNode(String id);
    void addNode(Node node);
    void updateNode(String id, Node node);
    void deleteNode(String id);
}
