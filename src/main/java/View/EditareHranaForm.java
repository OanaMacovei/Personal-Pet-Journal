package View;
import java.awt.Frame;
import dao.HranaData;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JOptionPane;
import model.Hrana;

/**
 * Clasa pentru editarea hreanei nou in sistem.
 * Include mecanisme de validare in timp real pentru campurile numerice si
 * gestioneaza interactiunea cu baza de date prin {@code hranaDao}
 * @author oana
 */

public class EditareHranaForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditareHranaForm.class.getName());
    private Integer ID_Animal;
    HranaData hranaDao;
    Hrana hranaDeEditat;
    private Frame parentFrame;
    
    /**
     * Constructor in care se initializeaza datele, se apeleaza metodele utilizate.
     * @param parent Fereastra MainFrame pentru centrarea dialogului
     * @param hranaDeEditat Obiect {@link Hrana}
     * @param ID_Animal ID-ul animalului
     */
    public EditareHranaForm(Frame parent, Hrana hranaDeEditat, Integer ID_Animal) {
        super(parent, "Editare Hrana: " + hranaDeEditat.getNumeHrana(), true);
        this.ID_Animal = ID_Animal;
        this.hranaDao = new HranaData();
        this.hranaDeEditat = hranaDeEditat;
        this.parentFrame = parent;
        initComponents();
        realTimeValidation(cantitateHranaTextField);
        realTimeValidation(nr_ziHranaTextField);
        incarcareDate();
        
        String textFromFieldForColor = tipHranaTextField.getText().trim();
        cantitateHranaTextField.setBackground(Color.getColor(textFromFieldForColor));
        nr_ziHranaTextField.setBackground(Color.getColor(textFromFieldForColor));
        
        Styles.stilizeazaPanouFormular(jPanel1, jPanel2);
        Styles.stilizeazaTextFields(numeHranaTextField, tipHranaTextField, cantitateHranaTextField, nr_ziHranaTextField);
        Styles.stilizeazaButoane(actualizareHranaButton, anulareHranaButton);
        Styles.stilizeazaLabeluriBold(jLabel1, jLabel2, jLabel3, jLabel4);
        actualizareHranaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        anulareHranaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    /**
     * Incarcarea datelor din obiectul Animal in componentele {@link JTextField}
     */
    public void incarcareDate(){
        numeHranaTextField.setText(hranaDeEditat.getNumeHrana());
        tipHranaTextField.setText(hranaDeEditat.getTipHrana());
        cantitateHranaTextField.setText(String.valueOf(hranaDeEditat.getCantitate()));
        nr_ziHranaTextField.setText(String.valueOf(hranaDeEditat.getnrPerZi()));
    }

    /**
     * Se ataseaza un {@link DocumentListener} unui camp text pentru validare instantanee.
     * Se schimba culoarea fundalului in functie de validarea datelor.
     * @param textField Componenta {@link JTextField}
     */
    public void realTimeValidation(JTextField textField){
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validareSiCuloareTextField(textField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validareSiCuloareTextField(textField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validareSiCuloareTextField(textField);
            }
        });
    }
    
    /**
     * Se verifica daca textul dintr-un camp este pozitiv si valid.
     * @param textField Camp ce trebe validat
     * @return true, daca valoarea este valida; false, contrar
     */
    public boolean validareSiCuloareTextField(JTextField textField){
        String textFromField = textField.getText().trim();
        
        try {
            Double valDouble = Double.parseDouble(textFromField);
            
            if (valDouble <= 0){
               textField.setBackground(new Color(255, 200, 200));
               return false;
           }
           
           
           if (textField == nr_ziHranaTextField && valDouble != valDouble.intValue()){
               textField.setBackground(new Color(255, 200, 200));
               return false;
           }
           
           textField.setBackground(Color.getColor(textFromField));
           return true;
        } catch (NumberFormatException e) {
            textField.setBackground(new Color(255, 200, 200));
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        actualizareHranaButton = new javax.swing.JButton();
        anulareHranaButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numeHranaTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tipHranaTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cantitateHranaTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nr_ziHranaTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        actualizareHranaButton.setText("Actualizeaza");
        actualizareHranaButton.addActionListener(this::actualizareHranaButtonActionPerformed);
        jPanel1.add(actualizareHranaButton);

        anulareHranaButton.setText("Anulare");
        anulareHranaButton.setPreferredSize(new java.awt.Dimension(101, 27));
        anulareHranaButton.addActionListener(this::anulareHranaButtonActionPerformed);
        jPanel1.add(anulareHranaButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Nume:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 69;
        gridBagConstraints.insets = new java.awt.Insets(6, 19, 6, 19);
        jPanel2.add(numeHranaTextField, gridBagConstraints);

        jLabel2.setText("Tip:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 69;
        gridBagConstraints.insets = new java.awt.Insets(6, 19, 6, 19);
        jPanel2.add(tipHranaTextField, gridBagConstraints);

        jLabel3.setText("Cantitate:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 69;
        gridBagConstraints.insets = new java.awt.Insets(6, 19, 6, 19);
        jPanel2.add(cantitateHranaTextField, gridBagConstraints);

        jLabel4.setText("Nr/zi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 69;
        gridBagConstraints.insets = new java.awt.Insets(6, 19, 6, 19);
        jPanel2.add(nr_ziHranaTextField, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Gestioneaza evenimentul declansat de apasarea butonului de actualizare.
     * Colecteaza date, se apeleaza metodele de validare instantanee si {@code hranaDao} pentru salvarea datelor.
     * @param evt Eveniment
     */
    private void actualizareHranaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizareHranaButtonActionPerformed
        String nume = numeHranaTextField.getText().trim();
        String tip = tipHranaTextField.getText().trim();
        String cantitate = cantitateHranaTextField.getText().trim();
        String portie = nr_ziHranaTextField.getText().trim();
        
        Double cantitateDouble = Double.parseDouble(cantitate);
        Integer portieInt = Integer.parseInt(portie);
        
        if (nume.isEmpty() || tip.isEmpty() || cantitate.isEmpty() || portie.isEmpty()){
            JOptionPane.showMessageDialog(this, "Toate campurile sunt obligatorii", "Validare", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validareSiCuloareTextField(cantitateHranaTextField) || !validareSiCuloareTextField(nr_ziHranaTextField)){
            JOptionPane.showMessageDialog(this, "Corecteaza erorile marcate intai!", "Eroare validare", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (cantitateDouble <= 0.0 ||  cantitateDouble > 100){
            JOptionPane.showMessageDialog(this, "Cantitatea trebuie sa se afle in intervalul [1, 100]", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (portieInt <= 0 ||  portieInt > 7){
            JOptionPane.showMessageDialog(this, "Nr/zi trebuie sa se afle in intervalul [1, 7]", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        hranaDeEditat.setNumeHrana(nume);
        hranaDeEditat.setTipHrana(tip);
        hranaDeEditat.setCantitate(cantitateDouble);
        hranaDeEditat.setNrPerZi(portieInt);
        boolean succes = hranaDao.updateHrana(hranaDeEditat);
        
        if (succes){
            JOptionPane.showMessageDialog(this, "Hrana a fost editata cu succes!");
            if (parentFrame instanceof View.MainFrame){
                ((View.MainFrame)parentFrame).incarcareDateHrana(ID_Animal);
            }
            
            this.revalidate();
            this.repaint();
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Eroare la editarea hranei!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_actualizareHranaButtonActionPerformed

    /**
     * Eveniment declansat la apasarea butonului de anulare.
     * @param evt Eveniment
     */
    private void anulareHranaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulareHranaButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulareHranaButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizareHranaButton;
    private javax.swing.JButton anulareHranaButton;
    private javax.swing.JTextField cantitateHranaTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nr_ziHranaTextField;
    private javax.swing.JTextField numeHranaTextField;
    private javax.swing.JTextField tipHranaTextField;
    // End of variables declaration//GEN-END:variables
}
