package View;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Vaccin;
import dao.VaccinuriData;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Clasa pentru adaugarea unui vaccin nou in sistem.
 * Include mecanisme de validare in timp real pentru campurile formate din siruri de caractere si 
 * gestioneaza interactiunea cu baza de date prin {@link VaccinuriData}
 * @author oana
 */

public class AdaugaVaccinForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdaugaVaccinForm.class.getName());
    private int ID_Animal;
    VaccinuriData vaccinDao;
    
    /**
     * Constructor in care se initializeaza datele, se apeleaza metodele utilizate.
     * @param parent Fereastra MainFrame pentru centrarea dialogului
     * @param modal Face fereastra MainFrame inaccesibila
     * @param ID_Animal ID-ul animalului
     */
    public AdaugaVaccinForm(Frame parent, boolean modal, int ID_Animal) {
        super(parent, "Adaugare Vaccin", modal);
        this.ID_Animal = ID_Animal;
        this.vaccinDao = new VaccinuriData();
        initComponents();
        dateChooser.setDate(new Date()); //trebe dupa initComponents() ca sa fie creat dateChooser
        realTimeValidation(numeTextField);
        
        Styles.stilizeazaPanouFormular(jPanel1, jPanel2);
        Styles.stilizeazaTextFields(numeTextField);
        Styles.stilizeazaButoane(adaugareVaccinButton, anulareVaccinButton);
        Styles.stilizeazaLabeluriBold(numeLabel, dataLabel);
        dateChooser.getCalendarButton().setBackground(Styles.DARK_GREY);
        adaugareVaccinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        anulareVaccinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Se ataseaza un {@link DocumentListener} unui camp text pentru validare instantanee.
     * Se schimba culoarea fundalului in functie de validarea datelor.
     * @param textField 
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
        String regexCaractere = "^[a-zA-Z][a-zA-Z\\s-]*[a-zA-Z]$";
        
        if (textFromField.matches(regexCaractere)){
            textField.setBackground(Color.getColor(textFromField));
            return true;
        }
        else {
            textField.setBackground(new Color(255, 200, 200));
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        adaugareVaccinButton = new javax.swing.JButton();
        anulareVaccinButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        numeLabel = new javax.swing.JLabel();
        numeTextField = new javax.swing.JTextField();
        dataLabel = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();

        adaugareVaccinButton.setText("Adauga");
        adaugareVaccinButton.addActionListener(this::adaugareVaccinButtonActionPerformed);
        jPanel1.add(adaugareVaccinButton);

        anulareVaccinButton.setText("Anulare");
        anulareVaccinButton.addActionListener(this::anulareVaccinButtonActionPerformed);
        jPanel1.add(anulareVaccinButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        numeLabel.setText("Nume:");

        dataLabel.setText("Data administrare:");

        dateChooser.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(numeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addComponent(dataLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeLabel)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataLabel)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Gestioneaza evenimentul declansat de apasarea butonului de adaugare.
     * Colecteaza date, se apeleaza metodele de validare instantanee si {@code vaccinDao} pentru salvarea datelor.
     * @param evt Eveniment
     */
    private void adaugareVaccinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugareVaccinButtonActionPerformed
        String nume = numeTextField.getText().trim();
        Date data = new Date();
        
        if (!validareSiCuloareTextField(numeTextField)){
            JOptionPane.showMessageDialog(this, "Corecteaza erorile marcate intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            data = dateChooser.getDate();
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
        
        Vaccin vaccinNou = new Vaccin(0, nume, data, ID_Animal);
        boolean succes = vaccinDao.creeareVaccin(vaccinNou);
        
        if (!succes){
            JOptionPane.showMessageDialog(this, "Eroare la adaugarea vaccinului", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Vaccinul a fost adaugat cu succes!");
            this.dispose();
        }
        
    }//GEN-LAST:event_adaugareVaccinButtonActionPerformed

    /**
     * Eveniment declansat la apasarea butonului de anulare.
     * @param evt Eveniment
     */
    private void anulareVaccinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulareVaccinButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulareVaccinButtonActionPerformed

    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new AdaugaVaccinForm().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugareVaccinButton;
    private javax.swing.JButton anulareVaccinButton;
    private javax.swing.JLabel dataLabel;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel numeLabel;
    private javax.swing.JTextField numeTextField;
    // End of variables declaration//GEN-END:variables
}
