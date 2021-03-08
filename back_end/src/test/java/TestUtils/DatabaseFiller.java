package TestUtils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseFiller {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/TestUtils/MockDataFiles/";

    @Test
    public void runDBFiller() {
        fillDatabase();
    }

    private void fillDatabase() {
        addUsers();
        addItems();
    }

    private void addUsers() {
        try {
            Scanner scanner = new Scanner(new File(CURRENT_DIRECTORY + "USER_MOCK_DATA.csv"));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                
            }
            scanner.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addItems() {

    }
}
