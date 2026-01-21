package model;
import java.util.Date;

/**
 * Clasa ce contine toate datele despre o inregistrare medicala ce face parte din istoricul medical al unui animal.
 * @author oana
 */

public class IstoricMedical {
    Integer ID_Animal, ID_IstoricMedical;
    Date data;
    String motiv, diagnostic, tratament, numeMedic;
    
    /**
     * Construlcor ce initializeaza obiectul.
     * @param ID_IstoricMedical ID-ul unei inregistrari medicale
     * @param ID_Animal ID-ul animalului
     * @param data Data consultatiei, interventiei medicale, etc
     * @param motiv Motivul participarii
     * @param diagnostic Diagnosticul stabilit
     * @param tratament Tratamentul sugerat
     * @param numeMedic Numele medicului in operatiune
     */
    public IstoricMedical(Integer ID_IstoricMedical, Integer ID_Animal, Date data, String motiv, String diagnostic, String tratament, String numeMedic){
        this.ID_Animal = ID_Animal;
        this.ID_IstoricMedical = ID_IstoricMedical;
        this.data = data;
        this.motiv = motiv;
        this.diagnostic = diagnostic;
        this.tratament = tratament;
        this.numeMedic = numeMedic;
    }
    
    /** @return Motivul operatiunii */
    public String getMotiv(){return motiv;}
    
    /** @return Data operatiunii */
    public Date getData(){return data;}
    
    /** @return ID-ul inregistrarii medicale */
    public Integer getID_istoricMedical(){return ID_IstoricMedical;}
    
    /** @return ID-ul animalului */
    public Integer getID_Animal(){return ID_Animal;}
    
    /** @return Numele medicului */
    public String getNumeMedic(){return numeMedic;}
    
    /** @return Diagnosticul
     */
    public String getDiagnostic(){return diagnostic;}
    
    /** @return Tratamentul */
    public String getTratament(){return tratament;}
    
    /**
     * Seteaza ID-ul animalului
     * @param ID_Animal 
     */
    public void setID_Animal(Integer ID_Animal){
        this.ID_Animal = ID_Animal;
    }
    
    /**
     * Seteaza ID-ul inregistrarii medicale
     * @param ID_IstoricMedical 
     */
    public void setID_IstoricMedical(Integer ID_IstoricMedical){
        this.ID_IstoricMedical = ID_IstoricMedical;
    }
    
    /**
     * Seteaza numele medicului
     * @param numeMedic 
     */
    public void setNumeMedic(String numeMedic){
        this.numeMedic = numeMedic;
    }
    
    /**
     * Seteaza motivul
     * @param motiv 
     */
    public void setMotiv(String motiv){
        this.motiv = motiv;
    }
    
    /**
     * Seteaza diagnosticul
     * @param diagnostic 
     */
    public void setDiagnostic(String diagnostic){
        this.diagnostic = diagnostic;
    }
    
    /**
     * Seteaza tratamemtul
     * @param tratament 
     */
    public void setTratament(String tratament){
        this.tratament = tratament;
    }
    
    /**
     * Seteaza data
     * @param data 
     */
    public void setData(Date data){
        this.data = data;
    }
    
//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("ID_istoricMedical: ").append(ID_IstoricMedical).append(" ID_animal: ").append(ID_Animal).append("\n");
//        sb.append("Data interventie: ").append(data).append("\n");
//        sb.append("Motiv: ").append(motiv).append("\n");
//        sb.append("Diagnostic: ").append(diagnostic).append("\n");
//        sb.append("Tratament: ").append(tratament).append("\n");
//        sb.append("Nume Medic: ").append(numeMedic).append("\n");
//        return sb.toString();
//    }
}
