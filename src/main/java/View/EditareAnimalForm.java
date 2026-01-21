package View;
import model.Animal;
import dao.AnimaleData;
import model.Gender;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Clasa pentru editarea unui animal in sistem.
 * Include mecanisme de validare in timp real pentru campurile numerice si
 * gestioneaza interactiunea cu baza de date prin {@code AnimaleData}
 * @author oana
 */

public class EditareAnimalForm extends javax.swing.JDialog{
    
    Animal animalDeEditat;
    AnimaleData animaleDao;
    private Frame parentFrame;
    
    /**
     * Constructorul in care se initializeaza datele, se apeleaza metodele utilizate.
     * @param parent Fereastra MainFrame pentru centrarea dialogului
     * @param animalDeEditat Obiect {@link Animal}
     */
    public EditareAnimalForm(Frame parent, Animal animalDeEditat) {
        super(parent, "Editare Animal: " + animalDeEditat.getNume(), true);
        this.animalDeEditat = animalDeEditat;
        this.animaleDao = new AnimaleData();
        this.parentFrame = parent;
        initComponents();
        incarcareForm();
        incarcareComboBox();
        realTimeValidation(varstaTextField);
        realTimeValidation(greutateTextField);
    }

    /**
     * Incarcarea datelor din obiectul Animal in componentele {@link JTextField}
     */
    public void incarcareForm(){
       numeTextField.setText(animalDeEditat.getNume());
       specieTextField.setText(animalDeEditat.getSpecie());
       rasaTextField.setText(animalDeEditat.getRasa());
       varstaTextField.setText(String.valueOf(animalDeEditat.getVarsta()));
       greutateTextField.setText(String.valueOf(animalDeEditat.getGreutate()));
       genComboBox.setSelectedItem(animalDeEditat.getGen().toString());
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
        specieLabel = new javax.swing.JLabel();
        numeLabel = new javax.swing.JLabel();
        varstaLabel = new javax.swing.JLabel();
        greutateLabel = new javax.swing.JLabel();
        genComboBox = new javax.swing.JComboBox<>();
        genLabel = new javax.swing.JLabel();
        rasaLabel = new javax.swing.JLabel();
        specieTextField = new javax.swing.JTextField();
        numeTextField = new javax.swing.JTextField();
        varstaTextField = new javax.swing.JTextField();
        greutateTextField = new javax.swing.JTextField();
        rasaTextField = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        actualizareAnimalButton = new javax.swing.JButton();
        anulareAnimalButton = new javax.swing.JButton();

        specieLabel.setText("Specie");

        numeLabel.setText("Nume:");

        varstaLabel.setText("Varsta:");

        greutateLabel.setText("Greutate:");

        genComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        genComboBox.addActionListener(this::genComboBoxActionPerformed);

        genLabel.setText("Gen:");

        rasaLabel.setText("Rasa:");

        specieTextField.addActionListener(this::specieTextFieldActionPerformed);

        numeTextField.addActionListener(this::numeTextFieldActionPerformed);

        actualizareAnimalButton.setText("Actualizeaza");
        actualizareAnimalButton.addActionListener(this::actualizareAnimalButtonActionPerformed);

        anulareAnimalButton.setText("Anulare");
        anulareAnimalButton.addActionListener(this::anulareAnimalButtonActionPerformed);

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPanelLayout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPanelLayout.createSequentialGroup()
                        .addComponent(actualizareAnimalButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anulareAnimalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPanelLayout.createSequentialGroup()
                        .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(greutateLabel)
                            .addComponent(genLabel)
                            .addComponent(varstaLabel)
                            .addComponent(rasaLabel)
                            .addComponent(specieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeLabel))
                        .addGap(30, 30, 30)
                        .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(greutateTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(varstaTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rasaTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(specieTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numeTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82))))
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeLabel)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(specieTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(specieLabel))
                .addGap(16, 16, 16)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rasaLabel)
                    .addComponent(rasaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varstaLabel)
                    .addComponent(varstaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(greutateLabel)
                    .addComponent(greutateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genLabel)
                    .addComponent(genComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actualizareAnimalButton)
                    .addComponent(anulareAnimalButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(dataPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void genComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genComboBoxActionPerformed
        
    }//GEN-LAST:event_genComboBoxActionPerformed

    /**
     * Gestioneaza evenimentul declansat de apasarea butonului de actualizare.
     * Colecteaza date, se apeleaza metodele de validare instantanee si {@code animaleDao} pentru salvarea datelor.
     * @param evt Eveniment
     */
    private void actualizareAnimalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizareAnimalButtonActionPerformed
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
            JOptionPane.showMessageDialog(this, "Varsta trebuie sa se afle in intervalul [1, 50]", "Eroare", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (greutate <= 0.0 || greutate > 100){
            JOptionPane.showMessageDialog(this, "Greutatea trebuie sa se afle in intervalul [1, 50]", "Eroare", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String genString = (String) genComboBox.getSelectedItem();
        Gender gen = Gender.valueOf(genString.toUpperCase().trim());

        animalDeEditat.setNume(nume);
        animalDeEditat.setSpecie(specie);
        animalDeEditat.setRasa(rasa);
        animalDeEditat.setVarsta(varsta);
        animalDeEditat.setGreutate(greutate);
        animalDeEditat.setGen(gen);
        boolean succes = animaleDao.updateAnimal(animalDeEditat);
        
        if (!succes){
            JOptionPane.showMessageDialog(this, "Eroare la adaugarea animalului", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Animalul a fost actualizat cu succes!");
            
            if (parentFrame instanceof View.MainFrame){
                ((View.MainFrame)parentFrame).incarcareDate();
            }
            this.dispose();
        }
        
    }//GEN-LAST:event_actualizareAnimalButtonActionPerformed

    private void specieTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specieTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_specieTextFieldActionPerformed

    private void numeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeTextFieldActionPerformed

    /**
     * Eveniment declansat la apasarea butonului de anulare.
     * @param evt Eveniment
     */
    private void anulareAnimalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulareAnimalButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulareAnimalButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizareAnimalButton;
    private javax.swing.JButton anulareAnimalButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JComboBox<String> genComboBox;
    private javax.swing.JLabel genLabel;
    private javax.swing.JLabel greutateLabel;
    private javax.swing.JTextField greutateTextField;
    private javax.swing.JLabel numeLabel;
    private javax.swing.JTextField numeTextField;
    private javax.swing.JLabel rasaLabel;
    private javax.swing.JTextField rasaTextField;
    private javax.swing.JLabel specieLabel;
    private javax.swing.JTextField specieTextField;
    private javax.swing.JLabel varstaLabel;
    private javax.swing.JTextField varstaTextField;
    // End of variables declaration//GEN-END:variables
}
