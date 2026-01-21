package model;
import java.util.ArrayList;

/**
 * Clasa ce contine toate datele despre hrana.
 * @author oana
 */

public class Hrana {
    String tipHrana, numeHrana;
    Double cantitate;
    Integer nrPerZi;
    Integer ID_Animal;
    ArrayList<String> oreAdministrare;

    /**
     * Constructor pentru initializarea obiectului.
     * @param numeHrana Numele hranei
     * @param tipHrana Tipul hranei
     * @param cantitate Cantitate
     * @param nrPerZi Portia pe zi
     * @param ID_Animal ID-ul animalului
     */
    public Hrana(String numeHrana, String tipHrana, Double cantitate, Integer nrPerZi, Integer ID_Animal){
        this.cantitate = cantitate;
        this.tipHrana = tipHrana;
        this.numeHrana = numeHrana;
        this.nrPerZi = nrPerZi;
        this.ID_Animal = ID_Animal;
        this.oreAdministrare = new ArrayList<>();
    }
    
    /** @return ID-ul animalului */
    public Integer getID_Animal(){return ID_Animal;}
    
    /** @return Numele hranei */
    public String getNumeHrana(){return numeHrana;}
    
    /** @return Tipul hranei */
    public String getTipHrana(){return tipHrana;}
    
    /** @return Portia pe zi */
    public Integer getnrPerZi(){return nrPerZi;}
    
    /** @return Cantitatea hranei */
    public Double getCantitate(){return cantitate;}
    
    /** @return Orele de administrare a hranei */
    public ArrayList<String> getOreAdministrare(){return oreAdministrare;}
    
    /**
     * Seteaza orele de administrare
     * @param oreAdministrare 
     */
    public void setOreAdministrare(ArrayList<String> oreAdministrare){
        this.oreAdministrare = oreAdministrare;
    }
    
    /**
     * Seteaza cantitatea
     * @param cantitate 
     */
    public void setCantitate(Double cantitate){
        this.cantitate = cantitate;
    }
    
    /**
     * Seteaza numele hranei
     * @param numeHrana 
     */
    public void setNumeHrana(String numeHrana){
        this.numeHrana = numeHrana;
    }
    
    /**
     * Seteaza tipul hranei
     * @param tipHrana 
     */
    public void setTipHrana(String tipHrana){
        this.tipHrana = tipHrana;
    }
    
    /**
     * Seteaza portia pe zi a hranei
     * @param nrPerZi 
     */
    public void setNrPerZi(Integer nrPerZi){
        this.nrPerZi = nrPerZi;
    }

//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("Tip Hrana: ").append(tipHrana).append("\n");
//        sb.append("Cantitate: ").append(cantitate).append("\n");
//        sb.append("Nr per zi: ").append(nrPerZi).append("\n");
//        return sb.toString();
//    }
}
