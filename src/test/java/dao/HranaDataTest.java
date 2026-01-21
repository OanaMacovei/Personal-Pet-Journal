package dao;
import java.util.ArrayList;
import model.Hrana;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HranaDataTest {
    HranaData hranaDao = new HranaData();
    
    @Test
    public void operatiiCRUD(){
        Integer ID_Animal = 4;
        ArrayList<String> ore = new ArrayList<>();
        ore.add("08:00");
        ore.add("12:00");
        Hrana hrana = new Hrana("Hrana Test", "Umeda", 25.2, 3, ID_Animal);
        hrana.setOreAdministrare(ore);
        
        boolean creereHrana = hranaDao.creeareHrana(hrana);
        assertTrue(creereHrana, "Inserare esuata");
        
        Hrana citire = hranaDao.getHranaPerAnimal(ID_Animal);
        assertNotNull(citire, "Citire esuata");
        assertEquals("Hrana Test", citire.getNumeHrana(), "Numele nu e egal");
        assertEquals(2, citire.getOreAdministrare().size(), "Converisa orelor nu a mers");
        
        
        boolean update;
        citire.setNumeHrana("Nume nou");
        citire.getOreAdministrare().add("16:00");
        update = hranaDao.updateHrana(citire);
        assertTrue(update, "Update esuat");
        
        boolean stergere = hranaDao.stergereHrana(ID_Animal);
        assertTrue(stergere, "Stergere esuata");       
    }
}
