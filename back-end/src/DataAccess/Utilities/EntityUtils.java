package DataAccess.Utilities;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;

public class EntityUtils {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static Map<String, String> hashPassword(String password) {
        Map<String, String> pair = new HashMap<>();

        try {
            byte[] salt = new byte[16];
            Random random = new Random();
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            pair.put(enc.encodeToString(salt), enc.encodeToString(hash));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.out.println("Error hashing password due to following exception: " + ex);
            pair.put(null, password);
        }

        return pair;
    }

    public static boolean idAIsLessThanIdB(String idA, String idB) {
        List<String> strings = new ArrayList<>();
        strings.add(idA);
        strings.add(idB);
        strings.sort(String.CASE_INSENSITIVE_ORDER);
        return strings.get(0).equals(idA);
    }
}
