package TestUtils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import DataAccess.DAO.Interfaces.IUserDAO;
import DataAccess.DAO.MySql.MySqlItemDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.Item;
import Entities.User;

public class DatabaseFiller {

    // region Properties

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/TestUtils/MockDataFiles/";

    private List<User> users;
    private List<Item> items;

    // endregion

    // region Public

    @BeforeEach
    public void setUp() {
        users = new ArrayList<>();
        items = new ArrayList<>();
    }

//    @Test
//    public void runDBFiller() {
//        fillDatabase();
//    }

    // endregion

    // region Private

    private void fillDatabase() {
        addUsers();
        addItems();
    }

    // region Table Fillers

    private void addUsers() {
        try {
            IUserDAO userDAO = new MySqlUserDAO();

            Scanner scanner = new Scanner(new File(CURRENT_DIRECTORY + "USER_MOCK_DATA.csv"));
            scanner.useDelimiter("\\n");

            while (scanner.hasNext()) {
                String line = scanner.next();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                String id = lineScanner.next();

                User user = new User(
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next()
                );
                user.setId(id);

                try {
                    userDAO.addUser(user);
                }
                catch (DatabaseException ex) {
                    System.out.println(ex.getMessage());
                }

                users.add(user);
            }
            scanner.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addItems() {
        try {
            IItemDAO itemDAO = new MySqlItemDAO();

            Scanner scanner = new Scanner(new File(CURRENT_DIRECTORY + "ITEM_MOCK_DATA.csv"));
            scanner.useDelimiter("\\n");

            while (scanner.hasNext()) {
                String line = scanner.next();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                Item item = new Item(
                        lineScanner.next(),
                        lineScanner.next(),
                        getCategory(),
                        lineScanner.next(),
                        lineScanner.nextBoolean(),
                        getOwnerId(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.nextInt(),
                        lineScanner.nextInt(),
                        lineScanner.nextInt(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next()
                );

                try {
                    itemDAO.addItem(item);
                }
                catch (DatabaseException ex) {
                    System.out.println(ex.getMessage());
                }

                items.add(item);
            }
            scanner.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // endregion

    // region Helper Methods

    private String getCategory() {
        Random rand = new Random();
        int upperBound = 3;
        int randomValue = rand.nextInt(upperBound);

        switch (randomValue) {
            case 0:
                return "MOVIE";
            case 1:
                return "BOOK";
            default:
                return "BOARD_GAME";
        }
    }

    private String getOwnerId() {
        Random rand = new Random();
        int upperBound = 1000;
        int randomValue = rand.nextInt(upperBound);

        return users.get(randomValue).getId();
    }

    // endregion

    // endregion
}
