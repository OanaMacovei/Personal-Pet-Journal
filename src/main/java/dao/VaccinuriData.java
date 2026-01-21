package dao;
import java.sql.Connection;
import java.util.ArrayList;
import model.Vaccin;
import java.text.SimpleDateFormat;
import db.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

/**
 * Clasa ce gestioneaza operatiile CRUD asupra tabelului Vaccinuri din baza de date.
 * @author oana
 */

public class VaccinuriData {

    /**
     * Prelucreaza lista de vaccinuri a unui animal specificat.
     * @param ID_Animal ID-ul animalului
     * @return Lista de tip {@link Vaccin}
     */
    public ArrayList<Vaccin> getVaccinuriPerAnimal(int ID_Animal){
        ArrayList<Vaccin> vaccinuri = new ArrayList<>();
        String query = "SELECT * FROM Vaccinuri WHERE ID_Animal = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, ID_Animal);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                Vaccin vaccin = new Vaccin(result.getInt("ID_Vaccin"), result.getString("nume_vaccin"), result.getDate("data_administrare"), result.getInt("ID_Animal"));
                vaccinuri.add(vaccin);
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return vaccinuri;
    }
    
    /**
     * Inserare vaccin nou in baza de date.
     * @param vaccin Obiect {@link Vaccin}
     * @return true, daca se insereaza corect; false, contrar
     */
    public boolean creeareVaccin(Vaccin vaccin){
        String query = "INSERT INTO Vaccinuri (nume_vaccin, data_administrare, ID_Animal) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            java.util.Date dataVaccin = vaccin.getData();
            long milisecunde = dataVaccin.getTime();
            Date dataSQL = new Date(milisecunde);
            
            
            pstmt.setString(1, vaccin.getNume());
            pstmt.setDate(2, dataSQL);
            pstmt.setInt(3, vaccin.getID_Animal());
            return pstmt.executeUpdate() > 0;
                    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Actualizarea datelor vaccinului existent in baza de date.
     * @param updatedVaccin Obiectul {@link Vaccin} ce contine toate datele noi
     * @return true, daca actualizarea este reusita; false, contrar
     */
    public boolean updateVaccin(Vaccin updatedVaccin){
        String query = "UPDATE Vaccinuri SET nume_vaccin = ?, data_administrare = ? WHERE ID_Vaccin = ?";
       
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            java.util.Date dataVaccin = updatedVaccin.getData();
            long milisecunde = dataVaccin.getTime();
            Date dataSQL = new Date(milisecunde);
            
            pstmt.setString(1, updatedVaccin.getNume());
            pstmt.setDate(2, dataSQL);
            pstmt.setInt(3, updatedVaccin.getID_Vaccin());
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Stergere vaccin din baza de date.
     * @param id ID-ul obiectului vaccin
     * @return true, daca stergerea este reusita; false, contrar
     */
    public boolean stergereVaccin(int id){
        String query = "DELETE FROM Vaccinuri WHERE ID_Vaccin = ?";
           
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){

         pstmt.setInt(1, id);
         return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
