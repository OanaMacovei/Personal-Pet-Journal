package View;

import com.toedter.calendar.DateUtil;
import dao.UsersDao;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.DateUtilizator;

public class SignUpView extends javax.swing.JFrame {
    
    UsersDao usersDao;
    
    public SignUpView() {
        initComponents();
        realTimeValidation(emailTextField, "email");
        realTimeValidation(numeTextField, "nume");
        realTimeValidation(telefonTextField, "telefon");
        this.usersDao = new UsersDao();
        
        Styles.stilizeazaPanouFormular(jPanel2, signUpButtonsPanel);

        Styles.stilizeazaTextFields(usernameTextField, passwordTextField, confirmarePasswordTextField, 
                                     numeTextField, emailTextField, telefonTextField);

        Styles.stilizeazaButoane(signUpButton, anulareButton);
        Styles.stilizeazaComboBox(rolComboBox);
        Styles.stilizeazaLabeluriBold(jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        anulareButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void realTimeValidation(JTextField textField, String tipValidare){
       textField.getDocument().addDocumentListener(new DocumentListener() {
           @Override
           public void insertUpdate(DocumentEvent e) {
               validareSiCuloareTextField(textField, tipValidare);
           }

           @Override
           public void removeUpdate(DocumentEvent e) {
               validareSiCuloareTextField(textField, tipValidare);
           }

           @Override
           public void changedUpdate(DocumentEvent e) {
               validareSiCuloareTextField(textField, tipValidare);
           }
       });
    }
    
    public boolean validareSiCuloareTextField(JTextField textField, String tipValidare){
        String textFromField = textField.getText().trim();
        String regexCaractere = "^[a-zA-Z0-9][a-zA-Z0-9\\s-]*[a-zA-Z0-9]$";
        String regexEmail = "^[a-zA-Z]*.[a-zA-Z]*\\d*@[a-zA-z]*.[a-zA-Z]*";
        String regexTelefon = "^[0-9]*";
        
        boolean valid = false;
        if (tipValidare.equals("email")){
            valid = textFromField.matches(regexEmail);
        }
        else if (tipValidare.equals("nume")) {
            valid = textFromField.matches(regexCaractere);
        }
        else if (tipValidare.equals("telefon")){
            valid = textFromField.matches(regexTelefon);
        }
        
        if (valid){
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

        signUpButtonsPanel = new javax.swing.JPanel();
        signUpButton = new javax.swing.JButton();
        anulareButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        confirmarePasswordTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        numeTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        rolComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        telefonTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        signUpButton.setText("Sign Up");
        signUpButton.addActionListener(this::signUpButtonActionPerformed);
        signUpButtonsPanel.add(signUpButton);

        anulareButton.setText("Anulare");
        anulareButton.addActionListener(this::anulareButtonActionPerformed);
        signUpButtonsPanel.add(anulareButton);

        getContentPane().add(signUpButtonsPanel, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("Confirmare parola:");

        jLabel4.setText("Nume:");

        jLabel5.setText("Email:");

        rolComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "STAPAN", "MEDIC" }));

        jLabel6.setText("Rol:");

        jLabel7.setText("Telefon:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTextField)
                            .addComponent(usernameTextField)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addComponent(confirmarePasswordTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 146, Short.MAX_VALUE))
                            .addComponent(numeTextField)
                            .addComponent(emailTextField)
                            .addComponent(telefonTextField))))
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(confirmarePasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(telefonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        String confirmareParola = confirmarePasswordTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String nrTelefon = telefonTextField.getText().trim();
        String nume = numeTextField.getText().trim();
        String rol = (String) rolComboBox.getSelectedItem();
        
        if (nume.isEmpty() || username.isEmpty() || password.isEmpty() || confirmareParola.isEmpty() || email.isEmpty() || nrTelefon.isEmpty() || rol.isEmpty()){
            JOptionPane.showMessageDialog(this, "Toate campurile sunt obligatorii", "Validare", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validareSiCuloareTextField(usernameTextField, "nume") || !validareSiCuloareTextField(usernameTextField, "nume") || !validareSiCuloareTextField(passwordTextField, "nume") || !validareSiCuloareTextField(confirmarePasswordTextField, "nume") || !validareSiCuloareTextField(emailTextField, "email") || !validareSiCuloareTextField(telefonTextField, "telefon") || !validareSiCuloareTextField(numeTextField, "nume")){
            JOptionPane.showMessageDialog(this, "Corecteaza erorile marcate intai!", "Eroare validare", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmareParola)){
            confirmarePasswordTextField.setBackground(new Color(255, 200, 200));
            JOptionPane.showMessageDialog(this, "Parolele nu coincid!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        DateUtilizator userNou = new DateUtilizator(username, password, email, nrTelefon, nume, rol);
        boolean succes = usersDao.signUpUser(username, password, nume, email, nrTelefon, rol);
        if (succes){
            JOptionPane.showMessageDialog(this, "User-ul a fost creat cu succes!");
            this.dispose();
            return;
        }
        else {
            JOptionPane.showMessageDialog(this, "Eroare la creearea user-ului!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void anulareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulareButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulareButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anulareButton;
    private javax.swing.JTextField confirmarePasswordTextField;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField numeTextField;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JComboBox<String> rolComboBox;
    private javax.swing.JButton signUpButton;
    private javax.swing.JPanel signUpButtonsPanel;
    private javax.swing.JTextField telefonTextField;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
