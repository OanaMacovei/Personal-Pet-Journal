package model;
import java.util.Date;

/**
 * Clasa ce contine toate datele despre un vaccin.
 * @author oana
 */

public class Vaccin {
    Integer ID_vaccin, ID_animal;
    String nume;
    Date data;
    
    /**
     * Constructor ce initializeaza obiectul.
     * @param ID_vaccin ID-ul vaccinului
     * @param nume Numele vaccinului
     * @param data Data in care s-a administrat vaccinul
     * @param ID_animal ID-ul animalului
     */
    public Vaccin(Integer ID_vaccin, String nume, Date data, Integer ID_animal){
        this.ID_animal = ID_animal;
        this.ID_vaccin = ID_vaccin;
        this.data = data;
        this.nume = nume;
    }
    
    /** @return ID-ul animalului */
    public Integer getID_Animal(){return ID_animal;}
    
    /** * @return ID-ul vaccinului */
    public Integer getID_Vaccin(){return ID_vaccin;}
    
    /** * @return Numele vaccinului */
    public String getNume(){return nume;}
    
    /** @return Data administrarii vaccinului */
    public Date getData(){return data;}
    
    /**
     * Seteaza ID-ul vaccinului
     * @param ID_vaccin 
     */
    public void setID_Vaccin(Integer ID_vaccin){
        this.ID_vaccin = ID_vaccin;
    }
    
    /**
     * Seteaza ID-ul animalului
     * @param ID_animal 
     */
    public void setID_Animal(Integer ID_animal){
        this.ID_animal = this.ID_animal;
    }
    
    /**
     * Seteaza numele vaccinului
     * @param nume 
     */
    public void setNume(String nume){
        this.nume = nume;
    }
    
    /**
     * Seteaza data administrarii vaccinului
     * @param data 
     */
    public void setData(Date data){
        this.data = data;
    }
    
//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("ID_vaccin: ").append(ID_vaccin).append("\n");
//        sb.append("Nume: ").append(nume).append("\n");
//        sb.append("Data: ").append(data).append("\n");
//        sb.append("ID_animal: ").append(ID_animal).append("\n");
//        return sb.toString();
//    }
}
