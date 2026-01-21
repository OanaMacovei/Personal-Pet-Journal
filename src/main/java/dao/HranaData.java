package dao;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Hrana;
import java.util.ArrayList;

/**
 * Clasa ce gestioneaza operatiile CRUD asupra tabelului Hrana din baza de date
 * @author oana
 */

public class HranaData {
    
    /**
     * Prelucreaza obiectul {@link Hrana} a unui animal specificat.
     * @param ID_Animal ID-ul animalului
     * @return Obiect Hrana
     */
    public Hrana getHranaPerAnimal(int ID_Animal){
        String query = "SELECT * FROM Hrana WHERE ID_Animal = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, ID_Animal);
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                Hrana hrana = new Hrana(result.getString("nume_hrana"), result.getString("tip_hrana"), result.getDouble("cantitate"), result.getInt("portie_hrana_per_zi"), result.getInt("ID_Animal"));
                
                String ore = result.getString("ore_administrare");
                ArrayList<String> listaOre = new ArrayList<>();
                if (ore != null && !ore.isEmpty()){
                    String[] parsare = ore.split(",");
                    for (String string : parsare){
                        listaOre.add(string);
                    }
                }
                hrana.setOreAdministrare(listaOre);
                return hrana;
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
        
    }
    
    /**
     * Stergere hrana din baza de date.
     * @param id ID-ul obiectului hrana
     * @return true, daca stergerea este reusita; false, contrar
     */
    public boolean stergereHrana(int id){
        String query = "DELETE FROM Hrana WHERE ID_Animal = ?";
           
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
     * Actualizarea datelor hranei existente in baza de date.
     * @param updatedHrana Obiectul {@link Hrana} ce contine toate informatiile noi
     * @return true, daca actualizarea este reusita; false, contrar
     */
    public boolean updateHrana(Hrana updatedHrana){
        String query = "UPDATE Hrana SET nume_hrana = ?, tip_hrana = ?, cantitate = ?, portie_hrana_per_zi = ?, ore_administrare = ? WHERE ID_Animal = ?";
       
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            ArrayList<String> ore = updatedHrana.getOreAdministrare();
            String oreSQL = String.join(",", ore);
            
            
            pstmt.setString(1, updatedHrana.getNumeHrana());
            pstmt.setString(2, updatedHrana.getTipHrana());
            pstmt.setDouble(3, updatedHrana.getCantitate());
            pstmt.setInt(4, updatedHrana.getnrPerZi());
            pstmt.setString(5, oreSQL);
            pstmt.setInt(6, updatedHrana.getID_Animal());
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Inserare hrana noua in baza de date.
     * @param hranaNoua Obiect {@link Hrana}
     * @return true, daca se insereaza corect; false, contrar
     */
    public boolean creeareHrana(Hrana hranaNoua){
        String query = "INSERT INTO Hrana (ID_Animal, nume_hrana, tip_hrana, cantitate, portie_hrana_per_zi, ore_administrare) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            ArrayList<String> ore = hranaNoua.getOreAdministrare();
            String oreSQL = String.join(",", ore);
            
            pstmt.setInt(1, hranaNoua.getID_Animal());
            pstmt.setString(2, hranaNoua.getNumeHrana());
            pstmt.setString(3, hranaNoua.getTipHrana());
            pstmt.setDouble(4, hranaNoua.getCantitate());
            pstmt.setInt(5, hranaNoua.getnrPerZi());
            pstmt.setString(6, oreSQL);
            return pstmt.executeUpdate() > 0;
                    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
