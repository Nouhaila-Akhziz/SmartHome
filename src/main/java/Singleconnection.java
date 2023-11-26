import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Singleconnection {
    private static String db;
    private static String user;
    private static String Encryptedpwd;
    private static String url;
    private static Connection connection;

    // Private constructor to prevent instantiation from outside
    private Singleconnection() {
        // No need to call getConnection() from the constructor
    }

    // Static initialization block
    static {
        try {
            // Replace placeholders with actual configuration details
            db = Config_lecture.getProperty("db");
            user = Config_lecture.getProperty("user");
            Encryptedpwd = Config_lecture.getProperty("Encryptedpwd");
            url = "jdbc:mysql://localhost:3306/" + db;

            System.out.println("db: " + db);
            System.out.println("user: " + user);
            System.out.println("Encryptedpwd: " + Encryptedpwd);
            System.out.println("url: " + url);

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("MySQL JDBC Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String decrypt(String encryptedValue, String key) throws Exception {
        // Ensure that the key is not null before using it
        if (key == null) {
            throw new RuntimeException("Encryption key is null");
        }

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
        return new String(decryptedBytes);
    }

    public static Connection getConnection() {
        try {
            // Log to check the URL before the connection
            System.out.println("Connecting to database with URL: " + url);

            // Decryption of the password
            String encryptionKey = Config_lecture.getProperty("EncryptionKey");

            // Ensure that the encryption key is not null before using it
            if (encryptionKey == null) {
                throw new RuntimeException("Encryption key is null");
            }

            String decryptedPwd = decrypt(Encryptedpwd, encryptionKey);

            System.out.println("Decrypted Password: " + decryptedPwd);

            // Use the decrypted password for the connection
            connection = DriverManager.getConnection(url, user, decryptedPwd);
            System.out.println("Database connection established successfully");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating database connection", e);
        }
    }
}
