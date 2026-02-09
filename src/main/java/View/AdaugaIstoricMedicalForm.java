package View;

import java.awt.Frame;
import java.text.SimpleDateFormat;
import dao.IstoricMedicalData;
import java.awt.Color;
import java.awt.Cursor;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.IstoricMedical;

/**
 * Clasa pentru adaugarea unei inregistrari noiin sistem.
 * Include mecanisme de validare in timp real pentru campurile formate din siruri de caractere si 
 * gestioneaza interactiunea cu baza de date prin {@link IstoricMedicalData}
 * @author oana
 */

public class AdaugaIstoricMedicalForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdaugaIstoricMedicalForm.class.getName());
    Integer ID_Animal;
    IstoricMedicalData istoricDao;
    
    /**
     * Constructorul in care se initializeaza datele, se apeleaza metodele utilizate si se mai mac mici ajustari la interfata.
     * @param parent Fereastra MainFrame pentru centrarea dialogului
     * @param ID_Animal ID-ul animalului
     */
    public AdaugaIstoricMedicalForm(Frame parent, Integer ID_Animal) {
        super(parent, "Adaugare istoric", true);
        this.ID_Animal = ID_Animal;
        this.istoricDao = new IstoricMedicalData();
        initComponents();
        dateChooser.setDate(new Date());
        realTimeValidation(motivIstoricTextField);
        realTimeValidation(numeMedicTextField);
        realTimeValidation(diagnosticIstoricTextField);    
        
        Styles.stilizeazaPanouFormular(jPanel1, jPanel2);
        Styles.stilizeazaTextFields(motivIstoricTextField, numeMedicTextField, diagnosticIstoricTextField);
        Styles.stilizeazaTextArea(tratamentIstoricTextField, jScrollPane1);
        Styles.stilizeazaButoane(adaugaIstoricButton, anulareIstoricButton);
        Styles.stilizeazaLabeluriBold(jLabel1, jLabel2, jLabel3, jLabel4, jLabel5);
        adaugaIstoricButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        anulareIstoricButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        adaugaIstoricButton = new javax.swing.JButton();
        anulareIstoricButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        motivIstoricTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        numeMedicTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        diagnosticIstoricTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tratamentIstoricTextField = new javax.swing.JTextArea();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        adaugaIstoricButton.setText("Adauga");
        adaugaIstoricButton.addActionListener(this::adaugaIstoricButtonActionPerformed);
        jPanel1.add(adaugaIstoricButton);

        anulareIstoricButton.setText("Anulare");
        anulareIstoricButton.addActionListener(this::anulareIstoricButtonActionPerformed);
        jPanel1.add(anulareIstoricButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Data:");

        jLabel2.setText("Motiv:");

        jLabel3.setText("Nume Medic:");

        jLabel4.setText("Diagnostic:");

        jLabel5.setText("Tratament:");

        tratamentIstoricTextField.setColumns(20);
        tratamentIstoricTextField.setRows(5);
        jScrollPane1.setViewportView(tratamentIstoricTextField);

        dateChooser.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(diagnosticIstoricTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addComponent(motivIstoricTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(numeMedicTextField, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(motivIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(numeMedicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(diagnosticIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    
    
    /**
     * Gestioneaza evenimentul declansat de apasarea butonului de adaugare.
     * Colecteaza date, se apeleaza metodele de validare instantanee si {@code istoricDao} pentru salvarea datelor.
     * @param evt Eveniment
     */
    private void adaugaIstoricButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaIstoricButtonActionPerformed
        String motiv = motivIstoricTextField.getText().trim();
        String numeMedic = numeMedicTextField.getText().trim();
        String diagnostic = diagnosticIstoricTextField.getText().trim();
        String tratament = tratamentIstoricTextField.getText().trim();
        Date data = new Date();        
        
        try {
            data = dateChooser.getDate();
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
        
        IstoricMedical istoricNou = new IstoricMedical(0, ID_Animal, data, motiv, numeMedic, diagnostic, tratament);
        boolean succes = istoricDao.creeareIstoricMedical(istoricNou);
        
        if (!succes){
            JOptionPane.showMessageDialog(this, "Eroare la adaugarea inregistrarii", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Inregistrarea a fost adaugat cu succes!");
            this.dispose();
        }
    }//GEN-LAST:event_adaugaIstoricButtonActionPerformed

    /**
     * Eveniment declansat la apasarea butonului de anulare.
     * @param evt Eveniment
     */
    private void anulareIstoricButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulareIstoricButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulareIstoricButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugaIstoricButton;
    private javax.swing.JButton anulareIstoricButton;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JTextField diagnosticIstoricTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField motivIstoricTextField;
    private javax.swing.JTextField numeMedicTextField;
    private javax.swing.JTextArea tratamentIstoricTextField;
    // End of variables declaration//GEN-END:variables
}
