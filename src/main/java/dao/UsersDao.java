package dao;
import db.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import model.DateUtilizator;

/**
 * Clasa ce gestioneaza operatiunile legate de utilizatori.
 * Se ocupa de verificarea utilizatorului si preluarea informatiilor sale.
 * @author oana
 */

public class UsersDao {
    
    /**
     * Verifica existenta unui utilizator in baza de date pe baza username-ului si parolei.
     * Metoda utilizata in procesul de Login.
     * @param username Username-ul utilizatorului
     * @param parola Parola utilizatorului
     * @return Obiect {@link DateUtilizator} ce contine ID-ul, username-ul si rolul (Stapan, Medic); null, contrar
     */
    public DateUtilizator getUser(String username, String parola){
        String query = "SELECT ID_User, rol, username FROM Utilizatori WHERE username = ? AND parola = ?";
        
        try (Connection con = DatabaseConnection.getConnection(); 
                PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, parola);
            System.out.println("Username: " +  username);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()){
                DateUtilizator date = new DateUtilizator(rs.getInt("ID_User"), rs.getString("username"), rs.getString("rol"));
                return date;
            }
         
        } catch (Exception e) {
            System.err.println("Eroare la verificarea login-ului: " + e.getMessage());
        }
        
        return null;
    }
}
