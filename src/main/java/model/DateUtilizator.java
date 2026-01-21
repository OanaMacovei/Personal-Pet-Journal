package model;

/**
 * Clasa ce retine toate informatiile utilizatorului curent logat.
 * Accesarea lor se face de oriunde deoarece toate variabilele si metodele sunt declarate static.
 * @author oana
 */

public class DateUtilizator {
    static Integer ID_User;
    static String username, rol;
    
    /**
     * Constructor ce initializeaza un obiect.
     * @param ID_User ID-ul utilizatorului logat
     * @param username Username-ul utilizatorului logat
     * @param rol Rolul utilizatorului logat (STAPAN, MEDIC)
     */
    public DateUtilizator(Integer ID_User, String username, String rol){
        this.ID_User = ID_User;
        this.username = username;
        this.rol = rol;
    }
    
    /** @return ID-ul utilizatorului */
    public static Integer getID_User(){return ID_User;}
    
    /** @return Username-ul */
    public static String getUsername(){return username;}
    
    /** @return Rolul */
    public static String getRol(){return rol;}
    
    /**
     * Seteaza global datele utilizatorului logat
     * @param id ID-ul utilizatorului
     * @param name Numele utilizatorului
     * @param role Rolul atribuit
     */
    public static void setDateUtilizator(Integer id, String name, String role){
        ID_User = id;
        username = name;
        rol = role;
    }
    
    /**
     * Resetarea valorilor atributelor pentru functionalitatea delogarii.
     */
    public static void delogare(){
        ID_User = null;
        rol = null;
    }
}

