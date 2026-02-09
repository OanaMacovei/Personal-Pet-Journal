package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clasa ce realizeaza conexiunea cu baza de date.
 * @author oana
 */

public class DatabaseConnection {   
    /**
     * @return Driver inregistrat ce se potriveste cu URL-ul conexiunii la baza de date.
     * @throws SQLException 
     */
    public static Connection getConnection() {
        Properties props = new Properties();
        try (FileInputStream fis =  new FileInputStream("database/db.properties")) {
            props.load(fis);
            
            return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
