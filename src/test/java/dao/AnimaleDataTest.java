package dao;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import model.Animal;
import org.junit.jupiter.api.Assertions;

public class AnimaleDataTest {
    AnimaleData animaleDao = new AnimaleData();
    
    @Test
    public void filtrareDupaStapan(){
        Integer ID_Stapan = 10, animaleTotal = 1;
        ArrayList<Animal> animaleStapan = new ArrayList<>();
        animaleStapan = animaleDao.getAnimalDupaID(ID_Stapan);
        Assertions.assertEquals(1, animaleStapan.size(), "Nr de animale gasite gresit");
        
        Assertions.assertNotNull(animaleStapan, "Returneaza null");
    }
    
    @Test
    public void filtrareDupaMedic(){
        ArrayList<Animal> animale = animaleDao.getAnimale();
        Assertions.assertNotNull(animale, "Nu sunt toate animalele aici");
    }
}
