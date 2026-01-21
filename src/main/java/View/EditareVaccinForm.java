
package View;
import dao.VaccinuriData;
import java.awt.Color;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import model.Vaccin;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Clasa pentru editarea unui vaccin in sistem.
 * Include mecanisme de validare in timp real pentru campurile formate din siruri de caractere si
 * gestioneaza interactiunea cu baza de date prin {@code VaccinuriData}
 * @author oana
 */

public class EditareVaccinForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditareVaccinForm.class.getName());
    private VaccinuriData vaccinuriDao;
    Vaccin vaccinDeEditat;
    Frame parentFrame;
    
    /**
     * Constructorul in care se initializeaza datele, se apeleaza metodele utilizate.
     * @param parent Fereastra MainFrame pentru centrarea dialogului
     * @param vaccinDeEditat Obiect {@link Vaccin}
     */
    public EditareVaccinForm(Frame parent, Vaccin vaccinDeEditat) {
        super(parent, "Editare vaccin - " + vaccinDeEditat.getNume(), true);
        this.parentFrame = parent;
        this.vaccinuriDao = new VaccinuriData();
        this.vaccinDeEditat = vaccinDeEditat;
        initComponents();
        incarcareForm();
        realTimeValidation(numeTextField);
    }
    
    /**
     * Incarcarea datelor din obiectul Vaccin in componentele {@link JTextField}
     */
    public void incarcareForm(){
        numeTextField.setText(vaccinDeEditat.getNume());
        dateChooser.setDate(vaccinDeEditat.getData());
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
     * Se verifica daca textul dintr-un camp respecta regex-ul.
     * @param textField Camp ce trebe validat
     * @return true, daca valoarea este valida; false, contrar
     */
    public boolean validareSiCuloareTextField(JTextField textField){
        String regexCaractere = "^[a-zA-Z][a-zA-Z\\s-]*[a-zA-Z]$";
        String textFromField = textField.getText().trim();
        
        if (!textFromField.matches(regexCaractere)){
            textField.setBackground(new Color(255, 200, 200));
            return false;
        }
        else {
            textField.setBackground(Color.getColor(textFromField));
            return true;
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        actualizareVaccinButton = new javax.swing.JButton();
        anulareEditareVaccinButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        numeLabel = new javax.swing.JLabel();
        numeTextField = new javax.swing.JTextField();
        dataTextField = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(167, 37));

        actualizareVaccinButton.setText("Actualizeaza");
        actualizareVaccinButton.addActionListener(this::actualizareVaccinButtonActionPerformed);
        jPanel1.add(actualizareVaccinButton);

        anulareEditareVaccinButton.setText("Anulare");
        anulareEditareVaccinButton.setPreferredSize(new java.awt.Dimension(101, 27));
        anulareEditareVaccinButton.addActionListener(this::anulareEditareVaccinButtonActionPerformed);
        jPanel1.add(anulareEditareVaccinButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        numeLabel.setText("Nume:");

        dataTextField.setText("Data administrare:");

        dateChooser.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataTextField))
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeLabel)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataTextField))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Gestioneaza evenimentul declansat de apasarea butonului de actualizare.
     * Colecteaza date, se apeleaza metodele de validare instantanee si {@code animaleDao} pentru salvarea datelor.
     * @param evt Eveniment
     */
    private void actualizareVaccinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizareVaccinButtonActionPerformed
        String nume = numeTextField.getText().trim();
        Date data = dateChooser.getDate();
        
        if (!validareSiCuloareTextField(numeTextField)){
            JOptionPane.showMessageDialog(this, "Corecteaza erorile marcate intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        vaccinDeEditat.setNume(nume);
        vaccinDeEditat.setData(data);
        boolean succes = vaccinuriDao.updateVaccin(vaccinDeEditat);
        
        if (!succes){
            JOptionPane.showMessageDialog(this, "Eroare la editarea vaccinului", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            JOptionPane.showMessageDialog(this, "Vaccinul a fost actualizat cu succes!");
            
            if (parentFrame instanceof View.MainFrame){
                ((View.MainFrame)parentFrame).incarcareDateVaccinuri(vaccinDeEditat.getID_Animal());
            }
            this.vaccinDeEditat = null;
            this.dispose();
        }
    }//GEN-LAST:event_actualizareVaccinButtonActionPerformed

    /**
     * Eveniment declansat la apasarea butonului de anulare.
     * @param evt Eveniment
     */
    private void anulareEditareVaccinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulareEditareVaccinButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulareEditareVaccinButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizareVaccinButton;
    private javax.swing.JButton anulareEditareVaccinButton;
    private javax.swing.JLabel dataTextField;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel numeLabel;
    private javax.swing.JTextField numeTextField;
    // End of variables declaration//GEN-END:variables
}
