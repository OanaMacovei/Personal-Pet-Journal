package dao;
import java.util.ArrayList;
import model.Animal;
import model.Gender;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.DateUtilizator;

/**
 * Clasa ce gestioneaza operatiile CRUD asupra tabelului Animale din baza de date
 * @author oana
 */

public class AnimaleData {    

    /**
     * Prelucreaza lista tuturor animalelor din baza de date.
     * @return O lista de obiecte {@link Animal}
     */
    public ArrayList<Animal> getAnimale(){
        ArrayList<Animal> animale = new ArrayList<>();
        String query = "SELECT * FROM Animale";
         
        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(query)){
             
            while(result.next()){
                Animal animal = new Animal(result.getInt("ID_Animal"), result.getString("nume_animal"), result.getString("specie"), result.getString("rasa"), result.getInt("varsta"), result.getDouble("greutate"), Gender.valueOf(result.getString("gen")));
                animale.add(animal);
            }
             
        }catch (SQLException e){
            e.printStackTrace();
        }
        return animale;
    }
    
    /**
     * Inserare animal nou in baza de date.
     * Se atribuie ID-ul stapanului (utilizatorului curent).
     * @param animal Obiect {@link Animal}
     * @return true, daca inserarea a avut succes; false, contrar
     */
    public boolean creeareAnimal(Animal animal){
        String query = "INSERT INTO Animale (nume_animal, specie, rasa, varsta, greutate, gen, ID_Stapan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, animal.getNume());
            pstmt.setString(2, animal.getSpecie());
            pstmt.setString(3, animal.getRasa());
            pstmt.setInt(4, animal.getVarsta());
            pstmt.setDouble(5, animal.getGreutate());
            pstmt.setString(6, animal.getGen().toString());
            pstmt.setInt(7, DateUtilizator.getID_User());
            return pstmt.executeUpdate() > 0;
                    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Sterge animal din baza de date.
     * @param id ID-ul animalului
     * @return true, daca s-a sters; false, contrar
     */
    public boolean stergereAnimal(int id){
        String query = "DELETE FROM Animale WHERE ID_Animal = ?";
        
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
     * Actualizarea datelor unui animal existent in baza de date.
     * @param updatedAnimal Obiectul de tip {@link Animal} ce contine noile date.
     * @return true, daca actualizarea a reusit; false, contrar
     */
    public boolean updateAnimal(Animal updatedAnimal){
       String query = "UPDATE Animale SET nume_animal = ?, specie = ?, rasa = ?, varsta = ?, greutate = ?, gen = ? WHERE ID_Animal = ?";
       
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, updatedAnimal.getNume());
            pstmt.setString(2, updatedAnimal.getSpecie());
            pstmt.setString(3, updatedAnimal.getRasa());
            pstmt.setInt(4, updatedAnimal.getVarsta());
            pstmt.setDouble(5, updatedAnimal.getGreutate());
            pstmt.setString(6, updatedAnimal.getGen().toString());
            pstmt.setInt(7, updatedAnimal.getID());
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Filtrarea animalelor ce apartin unui anumit utilizator (stapan).
     * @param ID ID-ul utilizatorului
     * @return Lista cu animalele utilizatorului
     */
    public ArrayList<Animal> getAnimalDupaID(Integer ID){
        ArrayList<Animal> animaleStapan = new ArrayList<>();
        String query = "SELECT * FROM Animale WHERE ID_Stapan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, ID);
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()){
                Animal animal = new Animal(result.getInt("ID_Animal"), result.getString("nume_animal"), result.getString("specie"), result.getString("rasa"), result.getInt("varsta"), result.getDouble("greutate"), Gender.valueOf(result.getString("gen")));
                animaleStapan.add(animal);
            }
             
        }catch (SQLException e){
            e.printStackTrace();
        }
        return animaleStapan;
    }
    
    public boolean verificareDublura(String nume, String specie, String rasa, int ID_Stapan){
        String query = "SELECT COUNT(*) FROM Animale WHERE nume_animal = ? AND specie = ? AND rasa = ? AND ID_Stapan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, nume);
            pstmt.setString(2, specie);
            pstmt.setString(3, rasa);
            pstmt.setInt(4, ID_Stapan);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("Eroare la verificarea dublurii: " + e.getMessage());
        }
        return false;
    }
}
