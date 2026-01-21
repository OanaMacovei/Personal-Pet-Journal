package dao;

import model.DateUtilizator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsersDaoTest {
    UsersDao usersDao = new UsersDao();
    
    @Test
    public void loginSucces(){
        String username = "oanix";
        String parola = "1234";
        
        DateUtilizator succes = usersDao.getUser(username, parola);
        Assertions.assertNotNull(succes, "Loginul a esuat");
        System.out.println("Login cu succes!");
        
        Assertions.assertNotNull(succes.getRol(), "Userul nu are niciun rol");
        System.out.println("Userul are rolul " + succes.getRol());
    }
    
    @Test
    public void loginFail(){
        String username = "teochu";
        String parola = "aaa";
        
        DateUtilizator succes = usersDao.getUser(username, parola);
        Assertions.assertNull(succes, "Loginul trebuia sa esueze pt ca parola e gresita");
        System.out.println("Login esuat cu succes!");
    }
    
    @Test
    public void loginUserInexistent(){
        DateUtilizator date = usersDao.getUser("cevaUsername", "cevaParola");
        Assertions.assertNull(date, "Loginul trebuie sa returneze null");
        System.out.println("Test user inexistent: SUCCES");
    }
}
