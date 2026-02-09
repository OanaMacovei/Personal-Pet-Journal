package View;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Fereastra de autentificare a aplicatiei.
 * Reprezinta componenta View a procesului de Login.
 * @author oana
 */

public class LoginView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginView.class.getName());
    
    /**
     * Constructorul ferestrei Login.
     */
    public LoginView() {
        setTitle("Login");
        setLocationRelativeTo(null);
        initComponents();
        
        Styles.stilizeazaTextFields(usernameTextField, passwordField);
        Styles.stilizeazaButoane(loginButton, signUpButton);
        Styles.stilizeazaLabeluriBold(jLabel1, jLabel2, jLabel3);
        Styles.stilizeazaPanouFormular(jPanel2, loginButtonsPanel);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    /** @return Textul introdus pentru Username */
    public String getUsername() {return usernameTextField.getText();}
   
    /** @return Textrul introdus pentru parola */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    /**
     * Seteaza un Listener pentru evenimentul butonului de autentificare.
     * Permite sa se perceapa cand se face click.
     * @param listener Obiect {@link ActionListener}
     */
    public void setLoginListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
    
    /**
     * Afiseaza un mesaj de alerta cand ceva nu este in regula.
     * @param message
     */
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginButtonsPanel = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        showPasswordCheckBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginButton.setText("Intra in cont");
        loginButton.addActionListener(this::loginButtonActionPerformed);
        loginButtonsPanel.add(loginButton);

        getContentPane().add(loginButtonsPanel, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        showPasswordCheckBox.setText("Show");
        showPasswordCheckBox.addActionListener(this::showPasswordCheckBoxActionPerformed);

        jLabel3.setText("Nu ai cont? Creeaza aici!");

        signUpButton.setText("Sign Up");
        signUpButton.addActionListener(this::signUpButtonActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTextField)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(showPasswordCheckBox))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(signUpButton)
                        .addGap(74, 74, 74)))
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPasswordCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(signUpButton))
                .addGap(33, 33, 33))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        
    }//GEN-LAST:event_loginButtonActionPerformed

    private void showPasswordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxActionPerformed
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()){
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('*');
            }
        });
    }//GEN-LAST:event_showPasswordCheckBoxActionPerformed

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        SignUpView signUpForm = new SignUpView();
        signUpForm.pack();
        signUpForm.setLocationRelativeTo(this);
        signUpForm.setVisible(true);
    }//GEN-LAST:event_signUpButtonActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LoginView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginButtonsPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JCheckBox showPasswordCheckBox;
    private javax.swing.JButton signUpButton;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
