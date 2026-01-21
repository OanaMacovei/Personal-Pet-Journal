package View;
import dao.IstoricMedicalData;
import java.awt.Color;
import model.IstoricMedical;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Clasa pentru editarea unei inregistrari medicale in sistem.
 * Include mecanisme de validare in timp real pentru campurile numerice si formate din siruri de caractere si
 * gestioneaza interactiunea cu baza de date prin {@code IstoricMedicalData}
 * @author oana
 */

public class EditareIstoricMedicalForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditareIstoricMedicalForm.class.getName());
    private IstoricMedicalData istoriceDao;
    IstoricMedical istoricDeEditat;
    Frame parentFrame;
    
    /**
     * Constructorul in care se initializeaza datele, se apeleaza metodele utilizate.
     * @param parent Fereastra MainFrame pentru centrarea dialogului
     * @param istoricDeEditat Obiect {@link IstoricMedical}
     */
    public EditareIstoricMedicalForm(Frame parent, IstoricMedical istoricDeEditat) {
        super(parent, "Editare inregistrare", true);
        this.parentFrame = parent;
        this.istoriceDao = new IstoricMedicalData();
        this.istoricDeEditat = istoricDeEditat;
        initComponents();
        incarcareForm();
        dateChooser.setDate(new Date());
    }

    /**
     * Incarcarea datelor din obiectul IstoricMedical in componentele {@link JTextField}
     */
    public void incarcareForm(){
        numeIstoricTextField.setText(istoricDeEditat.getNumeMedic());
        tratamentIstoricTextField.setText(istoricDeEditat.getTratament());
        diagnosticIstoricTextField.setText(istoricDeEditat.getDiagnostic());
        motivIstoricTextField.setText(istoricDeEditat.getMotiv());
        
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateChooser.setDate(istoricDeEditat.getData());
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
        actualizareIstoricButton = new javax.swing.JButton();
        anulareIstoricTextField = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        motivIstoricTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        numeIstoricTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        diagnosticIstoricTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tratamentIstoricTextField = new javax.swing.JTextArea();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        actualizareIstoricButton.setText("Actualizeaza");
        actualizareIstoricButton.addActionListener(this::actualizareIstoricButtonActionPerformed);
        jPanel1.add(actualizareIstoricButton);

        anulareIstoricTextField.setText("Anulare");
        anulareIstoricTextField.setPreferredSize(new java.awt.Dimension(101, 27));
        anulareIstoricTextField.addActionListener(this::anulareIstoricTextFieldActionPerformed);
        jPanel1.add(anulareIstoricTextField);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Data:");

        jLabel2.setText("Motiv:");

        jLabel3.setText("Nume Medic:");

        jLabel4.setText("Diagnostic:");

        jLabel5.setText("Tratament:");

        tratamentIstoricTextField.setColumns(20);
        tratamentIstoricTextField.setLineWrap(true);
        tratamentIstoricTextField.setRows(5);
        tratamentIstoricTextField.setWrapStyleWord(true);
        jScrollPane1.setViewportView(tratamentIstoricTextField);

        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new java.awt.Dimension(111, 26));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(diagnosticIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(motivIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(numeIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(motivIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numeIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(diagnosticIstoricTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Gestioneaza evenimentul declansat de apasarea butonului de actualizare.
     * Colecteaza date, se apeleaza metodele de validare instantanee si {@code istoriceDao} pentru salvarea datelor.
     * @param evt Eveniment
     */
    private void actualizareIstoricButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizareIstoricButtonActionPerformed
        String motiv = motivIstoricTextField.getText().trim();
        String numeMedic = numeIstoricTextField.getText().trim();
        String diagnostic = diagnosticIstoricTextField.getText().trim();
        String tratament = tratamentIstoricTextField.getText().trim();
        Date data = new Date();        
        
        try {
            data = dateChooser.getDate();
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
        
        istoricDeEditat.setNumeMedic(numeMedic);
        istoricDeEditat.setMotiv(motiv);
        istoricDeEditat.setDiagnostic(diagnostic);
        istoricDeEditat.setTratament(tratament);
        istoricDeEditat.setData(data);
        boolean succes = istoriceDao.updateIstoricMedical(istoricDeEditat);
        
        if (!succes){
            JOptionPane.showMessageDialog(this, "Eroare la editarea inregistrarii", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            JOptionPane.showMessageDialog(this, "Inregistrarea a fost actualizat cu succes!");
            
            if (parentFrame instanceof View.MainFrame){
                ((View.MainFrame)parentFrame).incarcareDateIstoricMedical(istoricDeEditat.getID_Animal());
            }
            this.dispose();
        }
    }//GEN-LAST:event_actualizareIstoricButtonActionPerformed

    /**
     * Eveniment declansat la apasarea butonului de anulare.
     * @param evt Eveniment
     */
    private void anulareIstoricTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulareIstoricTextFieldActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulareIstoricTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizareIstoricButton;
    private javax.swing.JButton anulareIstoricTextField;
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
    private javax.swing.JTextField numeIstoricTextField;
    private javax.swing.JTextArea tratamentIstoricTextField;
    // End of variables declaration//GEN-END:variables
}
