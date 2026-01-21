package View;
import dao.AnimaleData;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Animal;
import model.Gender;

/**
 * Clasa pentru adaugarea unui animal nou in sistem.
 * Include mecanisme de validare in timp real pentru campurile numerice si 
 * gestioneaza interactiunea cu baza de date prin {@link AnimaleData}
 * @author oana
 */

public class AdaugaAnimalForm extends javax.swing.JDialog {
    
    AnimaleData animaleDao;
    
    /**
     * Constructorul in care se initializeaza datele, se apeleaza metodele utilizate si se mai mac mici ajustari la interfata.
     * @param parent Fereastra MainFrame pentru centrarea dialogului
     */
    public AdaugaAnimalForm(java.awt.Frame parent) {
        super(parent, "Adaugare Animal", true);
        this.animaleDao = new AnimaleData();
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(parent);
        incarcareComboBox();
        realTimeValidation(varstaTextField);
        realTimeValidation(greutateTextField);
    }

    /**
     * Incarcarea datelor in ColboBox cu valorile din enumerarea {@link Gender}
     */
    public void incarcareComboBox(){
        genComboBox.removeAllItems();
        
        for (Gender gen : Gender.values()){
            genComboBox.addItem(gen.name());
        }
        genComboBox.setSelectedIndex(0);
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
           
           
           if (textField == varstaTextField && valDouble != valDouble.intValue()){
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

        dataPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        adaugareButton = new javax.swing.JButton();
        stergereButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        numeLabel = new javax.swing.JLabel();
        specieLabel = new javax.swing.JLabel();
        rasaLabel = new javax.swing.JLabel();
        varstaLabel = new javax.swing.JLabel();
        greutateLabel = new javax.swing.JLabel();
        genLabel = new javax.swing.JLabel();
        numeTextField = new javax.swing.JTextField();
        specieTextField = new javax.swing.JTextField();
        rasaTextField = new javax.swing.JTextField();
        varstaTextField = new javax.swing.JTextField();
        greutateTextField = new javax.swing.JTextField();
        genComboBox = new javax.swing.JComboBox<>();

        dataPanel.setLayout(new java.awt.BorderLayout());

        adaugareButton.setText("Adaugare");
        adaugareButton.addActionListener(this::adaugareButtonActionPerformed);
        buttonPanel.add(adaugareButton);

        stergereButton.setText("Anulare");
        stergereButton.setPreferredSize(new java.awt.Dimension(86, 27));
        stergereButton.addActionListener(this::stergereButtonActionPerformed);
        buttonPanel.add(stergereButton);

        dataPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        numeLabel.setText("Nume:");

        specieLabel.setText("Specie:");

        rasaLabel.setText("Rasa:");

        varstaLabel.setText("Varsta:");

        greutateLabel.setText("Greutate:");

        genLabel.setText("Gen:");

        varstaTextField.addActionListener(this::varstaTextFieldActionPerformed);

        genComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(specieTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rasaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(specieLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(varstaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(greutateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(genLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(genComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rasaTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(varstaTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(greutateTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeLabel)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(specieLabel)
                    .addComponent(specieTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rasaLabel)
                    .addComponent(rasaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varstaLabel)
                    .addComponent(varstaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(greutateLabel)
                    .addComponent(greutateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genLabel)
                    .addComponent(genComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        dataPanel.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(dataPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Gestioneaza evenimentul declansat de apasarea butonului de adaugare.
     * Colecteaza date, se apeleaza metodele de validare instantanee si {@code animaleDao} pentru salvarea datelor.
     * @param evt Eveniment
     */
    private void adaugareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugareButtonActionPerformed
        String nume = numeTextField.getText().trim();
        String specie = specieTextField.getText().trim();
        String rasa = rasaTextField.getText().trim();
        String varstaString = varstaTextField.getText().trim();
        String greutateString = greutateTextField.getText().trim();

        if (nume.isEmpty() || specie.isEmpty() || rasa.isEmpty() || varstaString.isEmpty() || greutateString.isEmpty()){
            JOptionPane.showMessageDialog(this, "Toate campurile sunt obligatorii", "Validare", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validareSiCuloareTextField(varstaTextField) || !validareSiCuloareTextField(greutateTextField)){
            JOptionPane.showMessageDialog(this, "Corecteaza erorile marcate intai!", "Eroare validare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer varsta = Integer.parseInt(varstaTextField.getText().trim());
        Double greutate = Double.parseDouble(greutateTextField.getText().trim());

        if (varsta <= 0 || varsta > 50){
            JOptionPane.showMessageDialog(this, "Varsta trebuie sa se afle in intervalul [1, 50]", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (greutate <= 0.0 || greutate > 100){
            JOptionPane.showMessageDialog(this, "Greutatea trebuie sa se afle in intervalul [1, 50]", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String genString = (String) genComboBox.getSelectedItem();
        Gender gen = Gender.valueOf(genString.toUpperCase().trim());
        Animal animalNou = new Animal(null, nume, specie, rasa, varsta, greutate, gen);
        boolean succes = animaleDao.creeareAnimal(animalNou);

        if (!succes){
            JOptionPane.showMessageDialog(this, "Eroare la adaugarea animalului", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Animalul a fost adaugat cu succes!");
            this.dispose();
        }
    }//GEN-LAST:event_adaugareButtonActionPerformed

    private void varstaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varstaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_varstaTextFieldActionPerformed

    /**
     * Eveniment declansat la apasarea butonului de anulare.
     * @param evt Eveniment
     */
    private void stergereButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergereButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_stergereButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugareButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JComboBox<String> genComboBox;
    private javax.swing.JLabel genLabel;
    private javax.swing.JLabel greutateLabel;
    private javax.swing.JTextField greutateTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel numeLabel;
    private javax.swing.JTextField numeTextField;
    private javax.swing.JLabel rasaLabel;
    private javax.swing.JTextField rasaTextField;
    private javax.swing.JLabel specieLabel;
    private javax.swing.JTextField specieTextField;
    private javax.swing.JButton stergereButton;
    private javax.swing.JLabel varstaLabel;
    private javax.swing.JTextField varstaTextField;
    // End of variables declaration//GEN-END:variables
}
