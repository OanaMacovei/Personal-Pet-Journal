package dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.IstoricMedical;
import db.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Clasa ce gestioneaza operatiile CRUD asupra tabelului IstoricMedical din baza de date.
 * @author oana
 */

public class IstoricMedicalData {
    
    /**
     * Prelucreaza lista tuturor inregistrarilor medicale din baza de date.
     * @param ID_Animal ID-ul animalului
     * @return Lista de tip {@link IstoricMedical}
     */
    public ArrayList<IstoricMedical> getIstoricPerAnimal(int ID_Animal){
        ArrayList<IstoricMedical> istorice = new ArrayList<>();
        String query = "SELECT * FROM IstoricMedical WHERE ID_Animal = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, ID_Animal);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                IstoricMedical istoric = new IstoricMedical(result.getInt("ID_IstoricMedical"), result.getInt("ID_Animal"), result.getDate("data"), result.getString("motiv"), result.getString("diagnostic"), result.getString("tratament"), result.getString("nume_medic"));
                istorice.add(istoric);
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return istorice;      
    }
    
    
    /**
     * Inserare inregistrare noua in baza de date.
     * @param istoric Obiect {@link IstoricMedical}
     * @return true, daca se insereaza coret; false, contrar
     */
    public boolean creeareIstoricMedical(IstoricMedical istoric, Integer ID_Medic){
        String query = "INSERT INTO IstoricMedical (ID_Animal, data, motiv, diagnostic, tratament, nume_medic) VALUES (?, ?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE Animale SET id_medic = ? WHERE ID_Animal = ? AND id_medic IS NULL";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            PreparedStatement pstmtUpdate = conn.prepareStatement(updateQuery)) {
            
            java.util.Date dataIstoric = istoric.getData();
            long milisecunde = dataIstoric.getTime();
            Date dataSQL = new Date(milisecunde);
            
            pstmt.setInt(1, istoric.getID_Animal());
            pstmt.setDate(2, dataSQL);
            pstmt.setString(3, istoric.getMotiv());
            pstmt.setString(4, istoric.getDiagnostic());
            pstmt.setString(5, istoric.getTratament());
            pstmt.setString(6, istoric.getNumeMedic());
            int fisa = pstmt.executeUpdate();
            
            pstmtUpdate.setInt(1, ID_Medic);
            pstmtUpdate.setInt(2, istoric.getID_Animal());
            pstmtUpdate.executeUpdate();
            
            return fisa > 0;
                    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Stergere inregistrare medicala din baza de date.
     * @param id ID-ul inregistrarii medicale
     * @return true, daca se sterge corect; false, contrar
     */
    public boolean stergereIstoricMedical(int id){
        String query = "DELETE FROM IstoricMedical WHERE ID_IstoricMedical = ?";
           
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){

         pstmt.setInt(1, id);
         return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }    
    }
    
    /**
     * Actualizarea datelor inregistrarii medicale existente in baza de date.
     * @param updatedIstoricMedical Obiectul {@link IstoricMedical} ce contine datele noi
     * @return true, daca actualizarea este reusita; false, contrar
     */
    public boolean updateIstoricMedical(IstoricMedical updatedIstoricMedical){
        String query = "UPDATE IstoricMedical SET data = ?, motiv = ?, diagnostic = ?, tratament = ?, nume_medic = ? WHERE ID_IstoricMedical = ?";
       
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            java.util.Date dataVaccin = updatedIstoricMedical.getData();
            long milisecunde = dataVaccin.getTime();
            Date dataSQL = new Date(milisecunde);
            
            pstmt.setDate(1, dataSQL);
            pstmt.setString(2, updatedIstoricMedical.getMotiv());
            pstmt.setString(3, updatedIstoricMedical.getDiagnostic());
            pstmt.setString(4, updatedIstoricMedical.getTratament());
            pstmt.setString(5, updatedIstoricMedical.getNumeMedic());
            pstmt.setInt(6, updatedIstoricMedical.getID_istoricMedical());
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
