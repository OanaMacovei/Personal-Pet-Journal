package model;
import model.Gender;
import java.util.ArrayList;

/**
 * Clasa ce contine toate datele despre un animal.
 * @author oana
 */

public class Animal {
    Integer ID, varsta;
    String nume, specie, rasa;
    Double greutate;
    Gender gen;
    ArrayList<Vaccin> vaccinuri;
    
    /**
     * @return ID-ul animalului
     */
    public Integer getID(){return ID;}
    
    /**
     * @return Numele animalului 
     */
    public String getNume(){return nume;}
    
    /**
     * @return Varsta animalului
     */
    public Integer getVarsta(){return varsta;}
    
    /**
     * @return Specia animalului
     */
    public String getSpecie(){return specie;}
    
    /**
     * @return Rasa animalului
     */
    public String getRasa(){return rasa;}
    
    /**
     * @return Greutatea animalului
     */
    public Double getGreutate(){return greutate;}
    
    /**
     * @return Genul animalului
     */
    public Gender getGen(){return gen;}
    
    /**
     * @return Lista de vaccinuri a unui animal
     */
    public ArrayList<Vaccin> getVaccinuri(){return vaccinuri;}
    
    /**
     * Seteaza ID-ul animalului preluat din baza de date
     * @param ID 
     */
    public void setID(Integer ID){
        this.ID = ID;
    }
    
    /**
     * Seteaza numele animalului preluat din baza de date
     * @param nume 
     */
    public void setNume(String nume){
        this.nume = nume;
    }
    
    /**
     * Seteaza varsta animalului preluata din baza de date
     * @param varsta 
     */
    public void setVarsta(Integer varsta){
        this.varsta = varsta;
    }
    
    /**
     * Seteaza specia animalului preluata din baza de date
     * @param specie 
     */
    public void setSpecie(String specie){
        this.specie = specie;
    }
    
    /**
     * Seteaza rasa animalului preluata din baza de date
     * @param rasa 
     */
    public void setRasa(String rasa){
        this.rasa = rasa;
    }
    
    /**
     * Seteaza greutatea animalului preluata din baza de date
     * @param greutate 
     */
    public void setGreutate(Double greutate){
        this.greutate = greutate;
    }
    
    /**
     * Seteaza genul animalului preluat din baza de date
     * @param gen 
     */
    public void setGen(Gender gen){
        this.gen = gen;
    }
    
    /**
     * Seteaza lista de vaccinuri a animalului preluata din baza de date
     * @param vaccinuri 
     */
    public void setVaccinuri(ArrayList<Vaccin> vaccinuri){
        this.vaccinuri = vaccinuri;
    }
    
    /**
     * Constructor pentru initializarea obiectului.
     * @param ID ID-ul animalului
     * @param nume Numele
     * @param specie Specia 
     * @param rasa Rasa
     * @param varsta Varsta
     * @param greutate Greutatea
     * @param gen Genul
     */
    public Animal(Integer ID, String nume, String specie, String rasa, Integer varsta, Double greutate, Gender gen){
        this.ID = ID;
        this.nume = nume;
        this.varsta = varsta;
        this.specie = specie;
        this.rasa = rasa;
        this.gen = gen;
        this.greutate = greutate;
        this.vaccinuri = new ArrayList<>();
    }
    
//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("Animal: ").append(nume).append(" ID: ").append(ID).append("\n");
//        sb.append("Age: ").append(varsta).append("\n");
//        sb.append("Species: ").append(specie).append("\n");
//        sb.append("Race: ").append(rasa).append("\n");
//        sb.append("Gender: ").append(gen).append("\n");
//        sb.append("Weight: ").append(greutate).append("\n");
//        return sb.toString();
//    }
    
    /**
     * Metoda necesara compararii.
     * @return ID-ul animalului ca valoare hash
     */
    public int hashcode(){
        return this.ID;
    }
    
    /**
     * Metoda necesara pentru tratarea obiectelor egale ca unul.
     * @param obj Obiectul de comparat
     * @return true, daca ID-urile sunt identice
     */
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        
        Animal other = (Animal) obj;
        return this.ID.equals(other.ID);
    }
    
//    /**
//     * Adauga vaccin in lista de vaccinuri.
//     * @param vaccin 
//     */
//    public void adaugareVaccin(Vaccin vaccin){
//        this.vaccinuri.add(vaccin);
//    }
}
