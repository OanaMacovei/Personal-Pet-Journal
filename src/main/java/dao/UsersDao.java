package dao;
import db.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import model.DateUtilizator;
import java.sql.SQLException;

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
    
    public boolean signUpUser(String username, String password, String name, String email, String nrTelefon, String rol){
        String query = "INSERT INTO Utilizatori (username, parola, nume_complet, email, nr_telefon, rol) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setString(5, nrTelefon);
            pstmt.setString(6, rol);
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
