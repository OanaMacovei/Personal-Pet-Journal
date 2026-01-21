package View;
import model.Vaccin;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

/**
 * Model de tabel pentru afisarea obiectelor de tip {@link Vaccin}.
 * Extinde {@link AbstractTableModel} pentru a gestiona mapare atributelor vaccinurilor
 * pe coloanele tabelului din interfata grafica.
 * @author oana
 */

public class TabelVaccinModel extends AbstractTableModel{
    
    ArrayList<Vaccin> vaccinuri;
    
    /**
     * Constructor ce initializeaza modelul cu o lista de vaccinuri
     * @param vaccinuri Lista initiala de vaccinuri preluata din baza de date
     */
    public TabelVaccinModel(ArrayList<Vaccin> vaccinuri){
        this.vaccinuri = vaccinuri;
    }
    
    String[] coloane = {"Nume", "Data (dd/mm/yyyy)"};
    
    /** @return Numarul total de coloane din tabel */
    @Override
    public int getColumnCount(){
        return coloane.length;
    }
    
    /** @return Numarul total de randuri din tabel */
    @Override
    public int getRowCount(){
        return vaccinuri.size();
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
        Vaccin vaccin = vaccinuri.get(randIndex);
        
        SimpleDateFormat dataFormat = new  SimpleDateFormat("dd/MM/yyyy");
        
        switch (coloanaIndex){
            case 0: return vaccin.getNume();
            case 1: return dataFormat.format(vaccin.getData());
            default: return null;
        }
    }
    
    /**
     * Seteaza lista de animale si notifica tabelul pentru redesenare
     * @param vaccinuri Lista de vaccinuri
     */
    public void setVaccinuri(ArrayList<Vaccin> vaccinuri){
        this.vaccinuri = vaccinuri;
        fireTableDataChanged();
    }
    
    /**
     * Returneaza obiectul Vaccin de la o anumita pozitie din tabel
     * @param index Pozitia randului din tabel
     * @return Obiectul {@link Vaccin}
     */
    public Vaccin getVaccin(int index){
        return vaccinuri.get(index);
    }
}
