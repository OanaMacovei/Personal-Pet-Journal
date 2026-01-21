package View;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import model.IstoricMedical;
import java.text.SimpleDateFormat;

/**
 * Model de tabel pentru afisarea obiectelor de tip {@link IstoricMedical}.
 * Extinde {@link AbstractTableModel} pentru a gestiona mapare atributelor inregistrarilor medicale
 * pe coloanele tabelului din interfata grafica.
 * @author oana
 */

public class TabelIstoricMedicalModel extends AbstractTableModel{
    
    ArrayList<IstoricMedical> listaIstoricMedical;
    
    /**
     * Constructor ce initializeaza modelul cu o lista de inregistrari medicale
     * @param listaIstoricMedical Lista initiala de interogari medicale preluata din baza de date
     */
    public TabelIstoricMedicalModel(ArrayList<IstoricMedical> listaIstoricMedical){
   
        this.listaIstoricMedical = listaIstoricMedical;
    }
    
    String[] coloane = {"Data", "Motiv"};
    
    /** @return Numarul total de coloane din tabel */
    @Override
    public int getColumnCount(){
        return coloane.length;
    }
    
    /** @return Numarul total de randuri din tabel */
    @Override
    public int getRowCount(){
        return listaIstoricMedical.size();
    }
    
    /**
     * Returneaza numele coloanei pentru afisare in antet
     * @param index Indexul coloanei
     * @return String ce reprezinta titlul coloanei
     */
    @Override
    public String getColumnName(int index){
        return coloane[index];
    }
    
    
    /**
     * Determina valoarea ce va fi afisata intr-o celuta specifica
     * @param randIndex Indexul randului
     * @param coloanaIndex Indexul coloanei
     * @return Obiectul ce trebuie afisata in celula
     */
    @Override
    public Object getValueAt(int randIndex, int coloanaIndex){
        IstoricMedical istoric = listaIstoricMedical.get(randIndex);
        

        SimpleDateFormat dataFormat = new  SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Se cere valoarea pentru randul " + randIndex);
        switch (coloanaIndex){
            case 0: return dataFormat.format(istoric.getData());
            case 1: return istoric.getMotiv();
            default: return null;
        }
    }
    
    /**
     * Seteaza lista de interogari medicale si notifica tabelul pentru redesenare
     * @param listaIstoricMedical Lista de interogari medicale
     */
    public void setIstorice(ArrayList<IstoricMedical> listaIstoricMedical){
        this.listaIstoricMedical = listaIstoricMedical;
        fireTableDataChanged();
    }
    
    /**
     * Returneaza obiectul IstoricMedical de la o anumita pozitie din tabel
     * @param index Pozitia randului din tabel
     * @return {@link IstoricMedical}
     */
    public IstoricMedical getIstoricMedical(int index){
        return listaIstoricMedical.get(index);
    }
}
