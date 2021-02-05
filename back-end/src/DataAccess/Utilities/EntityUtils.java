package DataAccess.Utilities;

import java.util.UUID;

public class EntityUtils {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
