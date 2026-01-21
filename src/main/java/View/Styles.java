package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clasa utilitara pentru centralizarea stilului grafic.
 * @author oana
 */
public class Styles {

    public static final Color DEEP_TEAL = new Color(38, 70, 83);
    public static final Color LIGHT_MINT = new Color(240, 249, 248);
    public static final Color FROSTED_MINT = new Color(233, 250, 240);
    public static final Color DARK_GREY = new Color(51, 51, 51);
    public static final Color SELECTION_COLOR = new Color(60, 100, 95);

    /**
     * Aplica stilul vizual pentru un tabel si header-ul acestuia.
     */
    public static void stilizeazaTabel(JTable tabel, JScrollPane scrollPane) {
        tabel.setBackground(LIGHT_MINT);
        tabel.setSelectionBackground(SELECTION_COLOR);
        tabel.setSelectionForeground(Color.WHITE);
        tabel.setRowHeight(25);
        tabel.setFocusable(false);
        tabel.setShowVerticalLines(true);
        tabel.setGridColor(new Color(97, 137, 133));
        
        scrollPane.getViewport().setBackground(Color.WHITE);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(97, 137, 133));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 12));
        headerRenderer.setOpaque(true);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabel.getColumnCount(); i++) {
            tabel.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    /**
     * Stilizeaza un grup de butoane pentru a avea aspect unitar.
     */
    public static void stilizeazaButoane(JButton... butoane) {
        for (JButton b : butoane) {
            b.setBackground(DARK_GREY);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Segoe UI", Font.BOLD, 12));
            b.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    /**
     * Aplica stilul pentru campurile de introducere text.
     */
    public static void stilizeazaTextFields(JTextField... fields) {
        for (JTextField f : fields) {
            f.setBackground(Color.WHITE);
            f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(97, 137, 133), 1),
                BorderFactory.createEmptyBorder(2, 5, 2, 5)
            ));
        }
    }

    /**
     * Aplica o bordura cu titlu personalizata.
     */
    public static void aplicaBorduraSectiune(JPanel panou, String titlu, Color culoareTitlu) {
        panou.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(culoareTitlu, 2),
                titlu,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                culoareTitlu
        ));
    }
    
    /**
    * Seteaza textul pe bold si atribuie culoare.
    * @param labels Lista de etichete ce vor fi stilizate.
    */
    public static void stilizeazaLabeluriBold(JLabel... labels) {
        for (JLabel lbl : labels) {
            Font fontCurent = lbl.getFont();
            // Creăm un font nou bazat pe cel curent, dar cu atributul BOLD
            lbl.setFont(new Font(fontCurent.getName(), Font.BOLD, fontCurent.getSize()));
            // Opțional: poți seta și culoarea aici dacă vrei să fie toate la fel
            lbl.setForeground(new Color(51, 51, 51)); // Dark Grey
        }
    }  
}