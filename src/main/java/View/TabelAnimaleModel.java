package View;
import javax.swing.table.AbstractTableModel;
import model.Animal;
import java.util.ArrayList;

/**
 * Model de tabel pentru afisarea obiectelor de tip {@link Animal}.
 * Extinde {@link AbstractTableModel} pentru a gestiona mapare atributelor animalelor
 * pe coloanele tabelului din interfata grafica.
 * @author oana
 */

public class TabelAnimaleModel extends AbstractTableModel{
    ArrayList<Animal> animale;
    
    /**
     * Constructor ce initializeaza modelul cu o lista de animale
     * @param animale Lista initiala de animale preluata din baza de date
     */
    public TabelAnimaleModel (ArrayList<Animal> animale){
        this.animale = animale;
    }
    
    /**
     * Returneaza obiectul Animal de la o anumita pozitie din tabel
     * @param index Pozitia randului din tabel
     * @return Obiectul {@link Animal}
     */
    public Animal getAnimal(int index){return animale.get(index);}
    
    /**
     * Seteaza lista de animale si notifica tabelul pentru redesenare
     * @param animale Lista de animale
     */
    public void setAnimale(ArrayList<Animal> animale){
        this.animale = animale;
        fireTableDataChanged();
    }
    
    String[] coloane = {"ID", "Nume", "Specie", "Rasa", "Varsta", "Greutate", "Gen"};
    
    /** @return Numarul total de coloane din tabel */
    @Override
    public int getColumnCount(){
        return coloane.length;
    }
    
    /** @return Numarul total de randuri din tabel */
    @Override
    public int getRowCount(){
        return animale.size();
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
        Animal animal = animale.get(randIndex);
        
        switch(coloanaIndex){
            case 0: return animal.getID();
            case 1: return animal.getNume();
            case 2: return animal.getSpecie();
            case 3: return animal.getRasa();
            case 4: return animal.getVarsta();
            case 5: return animal.getGreutate();
            case 6: return animal.getGen();
            default: return null;
        }
    }
}
