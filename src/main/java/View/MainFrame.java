package View;
import model.Animal;
import View.TabelAnimaleModel;
import dao.AnimaleData;
import dao.HranaData;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import View.AdaugaAnimalForm;
import javax.swing.*;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.CardLayout;
import model.Vaccin;
import dao.VaccinuriData;
import java.text.SimpleDateFormat;
import java.util.Collections;
import model.Hrana;
import javax.swing.JSpinner;
import java.util.Date;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import dao.IstoricMedicalData;
import model.IstoricMedical;
import javax.swing.table.TableColumn;
import View.LoginView;
import Controller.Login;
import dao.UsersDao;
import java.awt.Dimension;
import model.DateUtilizator;

/**
 * Fereastra principala a aplicatiei.
 * Gestioneaza panoul central, navigarea CardLayout, interactiunea cu bazele de date
 * si restrictiile in functie de rolul utilizatorului (STAPAN, MEDIC)
 * @author oana
 */

public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    
    AnimaleData animaleDao = new AnimaleData();
    ArrayList<Animal> animale = animaleDao.getAnimale();
    TabelAnimaleModel tabelAnimaleModel;
    VaccinuriData vaccinuriDao = new VaccinuriData();
    TabelVaccinModel tabelVaccinModel;
    HranaData hranaDao = new HranaData();
    DefaultListModel<String> oreIntroduse = new DefaultListModel<>();
    TabelIstoricMedicalModel tabelIstoricMedicalModel;
    IstoricMedicalData istoricMedicalDao = new IstoricMedicalData();
    Integer ID_User;
    String rol;
    
    /**
     * Constructor ce initializeaza componentele, preia datele si configureaza
     * elementele vizuale ale tabelelor si panourilor
     */
    public MainFrame() {
        initComponents();     
        rol = DateUtilizator.getRol();
        ID_User = DateUtilizator.getID_User();
        listaOre.setModel(oreIntroduse);
        
        this.setTitle("Bine ai venit, " + DateUtilizator.getUsername() + "!");
        this.setResizable(false);
        incarcareDate();
        
        headerTabel();
        defaultDetaliiPanou();
        schimbarePanouDetaliiDinTabel();
        stylePanouDetalii();
       
        aplicaToateStilurile();
        actualizareDetaliiPanou();
        populareDateDetaliiIstoricMedical();
        
        realTimeValidation(cantitateHranaTextField);
        realTimeValidation(nr_ziHranaTextField);
      
        
        TabelAnimale.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (javax.swing.SwingUtilities.isLeftMouseButton(evt)) {
                actualizareDetaliiPanou();
            }
        }
        });
    }
    
    /**
     * Aplicarea stilurilor de design pe diferite componente
     * A fost creata o functie ce cheama metodele ce implementeaza aceste stiluri pentru
     * a mentine cat de cat codul organizat
     */
    public void aplicaToateStilurile() {
        Styles.stilizeazaTabel(TabelAnimale, jScrollPane2);
        Styles.stilizeazaTabel(TabelVaccinuri, jScrollPane3);
        Styles.stilizeazaTabel(TabelIstoricMedical, jScrollPane1);

        Styles.stilizeazaButoane(hranaButton, fisaMedicalaButton, vaccinuriButton, 
                                 backButton, adaugaVaccinButton, editareVaccinButton, stergereVaccinButton, delogareButton,
                                 istoricBackButton, adaugaIstoricButton, stergeIstoricButton, editeazaIstoricButton,
                                 creeareEditareHranaButton, stergeHranaButton, backHranaButton,
                                 adaugareOraButton, stergereOraButton);

        Styles.stilizeazaTextFields(tipHranaTextField, numeHranaTextField, nr_ziHranaTextField, cantitateHranaTextField );

        detaliiPanou.setBackground(Styles.DEEP_TEAL);
        hranaPanou.setBackground(Styles.LIGHT_MINT);
        istoricMedicalPanou.setBackground(Styles.LIGHT_MINT);
        vaccinuriPanou.setBackground(Styles.LIGHT_MINT);

        Styles.aplicaBorduraSectiune(detaliiPanou, "Detalii animal", Color.WHITE);
        Styles.aplicaBorduraSectiune(oreAdministrarePanel, "Ore Administrare", Styles.DEEP_TEAL);
        
        Styles.stilizeazaLabeluriBold(jLabel9, jLabel5, jLabel2, jLabel11, jLabel4, jLabel6, jLabel8, jLabel10);
    }
    
    /**
    * Stilizare panou principal prin adaugarea de culori, fonturi, borduri
    */
    public void stylePanouDetalii(){
        Color deepTeal = new Color(38, 70, 83); //97, 137, 133
        Color frostedMint = new Color(233, 250, 240); //209, 255, 215;
        Color darkGrey = new Color(51, 51, 51);     // Culoarea butoanelor din Main
        
        Font baseFont = numeLabel.getFont();
        Font boldInclinatFont = baseFont.deriveFont(Font.BOLD | Font.ITALIC);
        Font boldFont = baseFont.deriveFont(Font.BOLD);

        buttonsPanel.setBackground(new Color(51, 51, 51));
        numeLabel.setFont(baseFont.deriveFont(Font.BOLD, 18f));
        numeLabel.setForeground(Color.WHITE);
        rasaLabelInfo.setForeground(Color.WHITE);
        rasaLabelInfo.setFont(boldInclinatFont);
        greutateLabelInfo.setForeground(Color.WHITE);
        greutateLabelInfo.setFont(boldInclinatFont);
        genLabelInfo.setForeground(Color.WHITE);
        genLabelInfo.setFont(boldInclinatFont);
        
        rasaLabel.setForeground(Color.WHITE);
        rasaLabel.setFont(boldFont);
        
        greutateLabel.setForeground(Color.WHITE);
        greutateLabel.setFont(boldFont);
        genLabel.setForeground(Color.WHITE);
        genLabel.setFont(boldFont);
    }
    
    /**
     * Stilizarea header-ului
     */
    public void headerTabel(){
        TabelAnimale.getTableHeader().setResizingAllowed(false);
        TabelAnimale.getTableHeader().setReorderingAllowed(false);
        TabelAnimale.setRowHeight(25);
        TabelAnimale.setIntercellSpacing(new Dimension(10, 1));
    }
    
    
    public void defaultDetaliiPanou(){
        rasaLabel.setVisible(false);
        greutateLabel.setVisible(false);
        genLabel.setVisible(false);
        numeLabel.setVisible(false);
        vaccinuriButton.setVisible(false);
        hranaButton.setVisible(false);
        fisaMedicalaButton.setVisible(false);
        
        tratamentIstoricTextArea.setEditable(false);
        tratamentIstoricTextArea.setFocusable(false);
        tratamentIstoricTextArea.setLineWrap(true);
        tratamentIstoricTextArea.setWrapStyleWord(true);

        diagnosticIstoricTextField.setEditable(false);
        diagnosticIstoricTextField.setFocusable(false);
        
        splitPaneCentral.setEnabled(false);
    }
    
    /**
     * Aplica resrtictii vizuale in functie de rolul utilizatorului logat.
     * Medicii au acces doar la Fisa Medica, iar stapanii pot gestiona Hrana, Vaccinurile si Animalele.
     */
    public void restrictiiRol(){
        if (rol.equals("MEDIC")){
            adaugaAnimalButton.setVisible(false);
            editareAnimalButton.setVisible(false);
            stergeAnimalButton.setVisible(false);
            
            vaccinuriButton.setVisible(false);
            hranaButton.setVisible(false);
            fisaMedicalaButton.setVisible(true);
            
            java.awt.GridBagLayout layout = (java.awt.GridBagLayout) detaliiPanou.getLayout();
                java.awt.GridBagConstraints gbc = layout.getConstraints(fisaMedicalaButton);

                // Modificăm gbc pentru a-l centra
                gbc.gridx = 0; // Îl punem pe prima coloană
                gbc.gridwidth = 3; // Îi spunem să se întindă pe lățimea celor 3 coloane inițiale
                gbc.anchor = java.awt.GridBagConstraints.CENTER; // Centrare absolută
                gbc.insets = new java.awt.Insets(100, 0, 100, 0); // Păstrăm spațierea de jos

                layout.setConstraints(fisaMedicalaButton, gbc);
            
        }
        else {
            adaugaIstoricButton.setVisible(false);
            stergeIstoricButton.setVisible(false);
            editeazaIstoricButton.setVisible(false);
            
            adaugaAnimalButton.setVisible(true);
            editareAnimalButton.setVisible(true);
            stergeAnimalButton.setVisible(true);
            
            vaccinuriButton.setVisible(true);
            hranaButton.setVisible(true);
            fisaMedicalaButton.setVisible(true);
            
            adaugaIstoricButton.setEnabled(false);
            stergeIstoricButton.setEnabled(false);
            editeazaIstoricButton.setEnabled(false);
        }
    }
    
    /**
     * Schimba continutul panoului dinamic in functie de apasarea
     * unui animal din tabel
     * Se utilizeaza {@link CardLayout}
     */
    public void schimbarePanouDetaliiDinTabel(){
        TabelAnimale.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                int randSelectat = TabelAnimale.getSelectedRow();
                
                if (randSelectat != -1){
                    actualizareDetaliiPanou();
                    CardLayout cardLayout = (CardLayout) containerDinamic.getLayout();
                    cardLayout.show(containerDinamic, "cardDetalii");
                }
            }
        });
    }
    
 
    
    /**
     * Afisarea campurilor legat de datele generale ale animalului
     */
    public void actualizareDetaliiPanou(){
        TabelAnimale.requestFocusInWindow();
        int randSelectat = TabelAnimale.getSelectedRow();

        if (randSelectat >= 0){
            int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
            Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
            numeLabel.setText(animalSelectat.getNume());
            numeLabel.setVisible(true);
            rasaLabelInfo.setText(animalSelectat.getRasa());
            rasaLabelInfo.setVisible(true);
            rasaLabel.setVisible(true);
 
            greutateLabelInfo.setText(animalSelectat.getGreutate().toString());
            greutateLabelInfo.setVisible(true);
            greutateLabel.setVisible(true);
            
            
            genLabelInfo.setText(animalSelectat.getGen().toString());
            genLabelInfo.setVisible(true);
            genLabel.setVisible(true);
            
            
            restrictiiRol();
        }
        else {          
            rasaLabelInfo.setText(""); 
            greutateLabelInfo.setText("");
            genLabelInfo.setText("");
            vaccinuriButton.setVisible(false);
            fisaMedicalaButton.setVisible(false);
            hranaButton.setVisible(false);
            rasaLabelInfo.setVisible(false);
            greutateLabelInfo.setVisible(false);
            genLabelInfo.setVisible(false);
            numeLabel.setVisible(false);
            rasaLabel.setVisible(false);
            greutateLabel.setVisible(false);
            genLabel.setVisible(false);
        }
    }
    
    /**
     * Incarca lista de animale din baza de date.
     * Daca utilizatorul este Stapan, vor fi afisate doar animalele proprii,
     * iar daca este Medic se va afisa lista tuturor animalelor.
     */
    public void incarcareDate(){
        
        ArrayList<Animal> animaleFiltrate = new ArrayList<>();
        
        if (rol.equals("STAPAN")){
            animaleFiltrate = animaleDao.getAnimalDupaID(ID_User);
        }
        else {
            animaleFiltrate = animaleDao.getAnimale();
        }
        
        if (tabelAnimaleModel == null){
            tabelAnimaleModel = new TabelAnimaleModel(animaleFiltrate);
            TabelAnimale.setModel(tabelAnimaleModel); // TabelAnimale e numele tabelul
            tabelAnimaleModel.fireTableDataChanged();
        }
        else {
            tabelAnimaleModel.setAnimale(animaleFiltrate);
            tabelAnimaleModel.fireTableDataChanged();
        }
        
    }
    
    /**
     * Incarcare lista de vaccinuri din baza de date.
     * @param ID_Animal ID-ul animalului
     */
    public void incarcareDateVaccinuri(int ID_Animal){ 
        ArrayList<Vaccin> vaccinuri = vaccinuriDao.getVaccinuriPerAnimal(ID_Animal);
        
        if (tabelVaccinModel == null){
            tabelVaccinModel = new TabelVaccinModel(vaccinuri);
            TabelVaccinuri.setModel(tabelVaccinModel);
            tabelVaccinModel.fireTableDataChanged();
        }
        else {
            tabelVaccinModel.setVaccinuri(vaccinuri);
            tabelVaccinModel.fireTableDataChanged();
        }
        Styles.stilizeazaTabel(TabelVaccinuri, jScrollPane3);
        System.out.println("Date tabel pt animalul cu id: " + ID_Animal);
    }
    
    /**
     * Incarcare lista de inregistrari medicale din baza de date.
     * @param ID_Animal ID-ul animalului
     */
    public void incarcareDateIstoricMedical(Integer ID_Animal){
        ArrayList<IstoricMedical> istorice = istoricMedicalDao.getIstoricPerAnimal(ID_Animal);
        
        if (tabelIstoricMedicalModel == null){
            tabelIstoricMedicalModel = new TabelIstoricMedicalModel(istorice);
            TabelIstoricMedical.setModel(tabelIstoricMedicalModel);
            tabelIstoricMedicalModel.fireTableDataChanged();
        }
        else {
            tabelIstoricMedicalModel.setIstorice(istorice);
            tabelIstoricMedicalModel.fireTableDataChanged();
        }
       
        Styles.stilizeazaTabel(TabelIstoricMedical, jScrollPane3);
        System.out.println("Date tabel pt animalul cu id: " + ID_Animal);
    }
    
    /**
     * Gestioneaza mecanismul de selectie a inregistrarilor medicale.
     * Cand se selecteaza una din ele, se afiseaza datele.
     */
    public void populareDateDetaliiIstoricMedical(){
        TabelIstoricMedical.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int randSelectat = TabelIstoricMedical.getSelectedRow();
                
                if (randSelectat == -1){
                    return;
                }
                
                int modelRow = TabelIstoricMedical.convertRowIndexToModel(randSelectat);
                IstoricMedical istoricSelectat = tabelIstoricMedicalModel.getIstoricMedical(modelRow);
                
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
                dataIstoricLabel.setText(dataFormat.format(istoricSelectat.getData()));
                medicIstoricLabel.setText(istoricSelectat.getNumeMedic());
                diagnosticIstoricTextField.setText(istoricSelectat.getDiagnostic());
                tratamentIstoricTextArea.setText(istoricSelectat.getTratament());
                
            }
        });
    }
    
    /**
     * Incarcare obiectului Hrana din baza de date.
     * In functie de existenta obiectului, se afiseaza datele sau nu, iar butonul de salvare
     * se transforma din "Editare" in "Salvare" in functie de caz.
     * @param ID_Animal ID-ul animalului
     */
    public void incarcareDateHrana(int ID_Animal){
        Hrana hranaAnimalSelectat = hranaDao.getHranaPerAnimal(ID_Animal);
        oreIntroduse.clear();
        
        if (hranaAnimalSelectat != null){
            tipHranaTextField.setText(hranaAnimalSelectat.getTipHrana());
            cantitateHranaTextField.setText(hranaAnimalSelectat.getCantitate().toString());
            nr_ziHranaTextField.setText(hranaAnimalSelectat.getnrPerZi().toString());
            numeHranaTextField.setText(hranaAnimalSelectat.getNumeHrana());
            
            tipHranaTextField.setEditable(false);
            tipHranaTextField.setFocusable(false);
            tipHranaTextField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 0));

            cantitateHranaTextField.setEditable(false);
            cantitateHranaTextField.setFocusable(false);
            cantitateHranaTextField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 0));

            nr_ziHranaTextField.setEditable(false);
            nr_ziHranaTextField.setFocusable(false);
            nr_ziHranaTextField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 0));

            numeHranaTextField.setEditable(false);
            numeHranaTextField.setFocusable(false);
            numeHranaTextField.setBorder(BorderFactory.createEmptyBorder(4, 5, 4, 0));
            
            //ca sa reapara orele in lista cand schimb de la un animal la alturl
            ArrayList<String> oreSalvate = hranaAnimalSelectat.getOreAdministrare();
            System.out.println("Ore salvate size: " + oreSalvate.size());
            if (oreSalvate != null && !oreSalvate.isEmpty()){
                for (String ora : oreSalvate){
                    oreIntroduse.addElement(ora);
                }
            }
            
            creeareEditareHranaButton.setText("Editare");
        }
        else {
            tipHranaTextField.setText("");
            cantitateHranaTextField.setText("");
            numeHranaTextField.setText("");
            nr_ziHranaTextField.setText("");
            
            tipHranaTextField.setEditable(true);
            cantitateHranaTextField.setEditable(true);
            numeHranaTextField.setEditable(true);
            nr_ziHranaTextField.setEditable(true);
            
            tipHranaTextField.setFocusable(true);
            cantitateHranaTextField.setFocusable(true);
            numeHranaTextField.setFocusable(true);
            nr_ziHranaTextField.setFocusable(true);
            
            String textFromFieldForColor = tipHranaTextField.getText().trim();
            cantitateHranaTextField.setBackground(Color.getColor(textFromFieldForColor));
            nr_ziHranaTextField.setBackground(Color.getColor(textFromFieldForColor));
            
            creeareEditareHranaButton.setText("Salveaza");
        }
        
        hranaPanou.revalidate();
        hranaPanou.repaint();
    }
    
    /**
     * Realizeaza validarea in timp real a campurilor numerice
     * @param textField Componenta {@code testField}
     */
    public void realTimeValidation(JTextField textField){
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                validareSiCuloareTextField(textField);
            }
            
            public void changedUpdate(DocumentEvent e){
                validareSiCuloareTextField(textField);
            }
            
            public void removeUpdate(DocumentEvent e){
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

        splitPaneCentral = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelAnimale = new javax.swing.JTable();
        containerDinamic = new javax.swing.JPanel();
        detaliiPanou = new javax.swing.JPanel();
        vaccinuriButton = new javax.swing.JButton();
        fisaMedicalaButton = new javax.swing.JButton();
        hranaButton = new javax.swing.JButton();
        rasaLabel = new javax.swing.JLabel();
        genLabel = new javax.swing.JLabel();
        numeLabel = new javax.swing.JLabel();
        greutateLabel = new javax.swing.JLabel();
        rasaLabelInfo = new javax.swing.JLabel();
        greutateLabelInfo = new javax.swing.JLabel();
        genLabelInfo = new javax.swing.JLabel();
        vaccinuriPanou = new javax.swing.JPanel();
        vaccinuriButtonsPanel = new javax.swing.JPanel();
        adaugaVaccinButton = new javax.swing.JButton();
        editareVaccinButton = new javax.swing.JButton();
        stergereVaccinButton = new javax.swing.JButton();
        vaccinuriBackButtonPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        tabelPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelVaccinuri = new javax.swing.JTable();
        hranaPanou = new javax.swing.JPanel();
        hranaBackButtonPanel = new javax.swing.JPanel();
        backHranaButton = new javax.swing.JButton();
        hranaButtonsPanel = new javax.swing.JPanel();
        creeareEditareHranaButton = new javax.swing.JButton();
        stergeHranaButton = new javax.swing.JButton();
        dataPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        oreAdministrarePanel = new javax.swing.JPanel();
        oreAdministrareScrollPane = new javax.swing.JScrollPane();
        listaOre = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        adaugareOraButton = new javax.swing.JButton();
        stergereOraButton = new javax.swing.JButton();
        tipHranaTextField = new javax.swing.JTextField();
        cantitateHranaTextField = new javax.swing.JTextField();
        nr_ziHranaTextField = new javax.swing.JTextField();
        numeHranaTextField = new javax.swing.JTextField();
        istoricMedicalPanou = new javax.swing.JPanel();
        istoricBackButtonPanel = new javax.swing.JPanel();
        istoricBackButton = new javax.swing.JButton();
        istoricButtonPanel = new javax.swing.JPanel();
        adaugaIstoricButton = new javax.swing.JButton();
        editeazaIstoricButton = new javax.swing.JButton();
        stergeIstoricButton = new javax.swing.JButton();
        istoricBigPanel = new javax.swing.JPanel();
        istoricTabelPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelIstoricMedical = new javax.swing.JTable();
        istoricDetaliiPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dataIstoricLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        medicIstoricLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        diagnosticIstoricTextField = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tratamentIstoricTextArea = new javax.swing.JTextArea();
        buttonsPanel = new javax.swing.JPanel();
        delogareButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(317, 0), new java.awt.Dimension(25, 32767));
        adaugaAnimalButton = new javax.swing.JButton();
        editareAnimalButton = new javax.swing.JButton();
        stergeAnimalButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        splitPaneCentral.setDividerLocation(800);
        splitPaneCentral.setPreferredSize(new java.awt.Dimension(1068, 597));

        TabelAnimale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelAnimale.setGridColor(new java.awt.Color(97, 137, 133));
        TabelAnimale.setShowGrid(false);
        TabelAnimale.setShowVerticalLines(true);
        jScrollPane2.setViewportView(TabelAnimale);

        splitPaneCentral.setLeftComponent(jScrollPane2);

        containerDinamic.setLayout(new java.awt.CardLayout());

        detaliiPanou.setLayout(new java.awt.GridBagLayout());

        vaccinuriButton.setText("Vaccinuri");
        vaccinuriButton.addActionListener(this::vaccinuriButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(100, 0, 100, 0);
        detaliiPanou.add(vaccinuriButton, gridBagConstraints);

        fisaMedicalaButton.setText("Fisa Medicala");
        fisaMedicalaButton.addActionListener(this::fisaMedicalaButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        detaliiPanou.add(fisaMedicalaButton, gridBagConstraints);

        hranaButton.setText("Hrana");
        hranaButton.addActionListener(this::hranaButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        detaliiPanou.add(hranaButton, gridBagConstraints);

        rasaLabel.setText("Rasa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 13, 10);
        detaliiPanou.add(rasaLabel, gridBagConstraints);

        genLabel.setText("Gen:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 13, 10);
        detaliiPanou.add(genLabel, gridBagConstraints);

        numeLabel.setText("Nume");
        numeLabel.setMaximumSize(new java.awt.Dimension(70, 30));
        numeLabel.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 30, 10);
        detaliiPanou.add(numeLabel, gridBagConstraints);

        greutateLabel.setText("Greutate:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 13, 10);
        detaliiPanou.add(greutateLabel, gridBagConstraints);

        rasaLabelInfo.setText("rasaLabelData");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 13, 10);
        detaliiPanou.add(rasaLabelInfo, gridBagConstraints);

        greutateLabelInfo.setText("greutateLabelData");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 13, 10);
        detaliiPanou.add(greutateLabelInfo, gridBagConstraints);

        genLabelInfo.setText("genLabelData");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 13, 10);
        detaliiPanou.add(genLabelInfo, gridBagConstraints);

        containerDinamic.add(detaliiPanou, "cardDetalii");

        vaccinuriPanou.setLayout(new java.awt.BorderLayout());

        adaugaVaccinButton.setText("Adauga");
        adaugaVaccinButton.addActionListener(this::adaugaVaccinButtonActionPerformed);
        vaccinuriButtonsPanel.add(adaugaVaccinButton);

        editareVaccinButton.setText("Editeaza");
        editareVaccinButton.addActionListener(this::editareVaccinButtonActionPerformed);
        vaccinuriButtonsPanel.add(editareVaccinButton);

        stergereVaccinButton.setText("Sterge");
        stergereVaccinButton.addActionListener(this::stergereVaccinButtonActionPerformed);
        vaccinuriButtonsPanel.add(stergereVaccinButton);

        vaccinuriPanou.add(vaccinuriButtonsPanel, java.awt.BorderLayout.SOUTH);

        vaccinuriBackButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        backButton.setText("Back");
        backButton.addActionListener(this::backButtonActionPerformed);
        vaccinuriBackButtonPanel.add(backButton);

        vaccinuriPanou.add(vaccinuriBackButtonPanel, java.awt.BorderLayout.NORTH);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(263, 482));

        TabelVaccinuri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelVaccinuri.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane3.setViewportView(TabelVaccinuri);

        tabelPanel.add(jScrollPane3);

        vaccinuriPanou.add(tabelPanel, java.awt.BorderLayout.CENTER);

        containerDinamic.add(vaccinuriPanou, "cardVaccinuri");

        hranaPanou.setLayout(new java.awt.BorderLayout());

        hranaBackButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        backHranaButton.setText("Back");
        backHranaButton.addActionListener(this::backHranaButtonActionPerformed);
        hranaBackButtonPanel.add(backHranaButton);

        hranaPanou.add(hranaBackButtonPanel, java.awt.BorderLayout.PAGE_START);

        creeareEditareHranaButton.addActionListener(this::creeareEditareHranaButtonActionPerformed);
        hranaButtonsPanel.add(creeareEditareHranaButton);

        stergeHranaButton.setText("Sterge");
        stergeHranaButton.addActionListener(this::stergeHranaButtonActionPerformed);
        hranaButtonsPanel.add(stergeHranaButton);

        hranaPanou.add(hranaButtonsPanel, java.awt.BorderLayout.PAGE_END);

        dataPanel.setLayout(new java.awt.GridBagLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Tip:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 0);
        dataPanel.add(jLabel6, gridBagConstraints);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Cantitate:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 0);
        dataPanel.add(jLabel8, gridBagConstraints);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nr/zi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 0);
        dataPanel.add(jLabel10, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nume:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 0);
        dataPanel.add(jLabel4, gridBagConstraints);

        oreAdministrarePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 10))); // NOI18N
        oreAdministrarePanel.setLayout(new java.awt.BorderLayout());

        oreAdministrareScrollPane.setBorder(null);

        listaOre.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        oreAdministrareScrollPane.setViewportView(listaOre);

        oreAdministrarePanel.add(oreAdministrareScrollPane, java.awt.BorderLayout.CENTER);

        adaugareOraButton.setText("+");
        adaugareOraButton.addActionListener(this::adaugareOraButtonActionPerformed);
        jPanel3.add(adaugareOraButton);

        stergereOraButton.setText("-");
        stergereOraButton.addActionListener(this::stergereOraButtonActionPerformed);
        jPanel3.add(stergereOraButton);

        oreAdministrarePanel.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        dataPanel.add(oreAdministrarePanel, gridBagConstraints);

        tipHranaTextField.setOpaque(true);
        tipHranaTextField.addActionListener(this::tipHranaTextFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 8);
        dataPanel.add(tipHranaTextField, gridBagConstraints);

        cantitateHranaTextField.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 8);
        dataPanel.add(cantitateHranaTextField, gridBagConstraints);

        nr_ziHranaTextField.setOpaque(true);
        nr_ziHranaTextField.addActionListener(this::nr_ziHranaTextFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 8);
        dataPanel.add(nr_ziHranaTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        dataPanel.add(numeHranaTextField, gridBagConstraints);

        hranaPanou.add(dataPanel, java.awt.BorderLayout.CENTER);

        containerDinamic.add(hranaPanou, "cardHrana");

        istoricMedicalPanou.setLayout(new java.awt.BorderLayout());

        istoricBackButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        istoricBackButton.setText("Back");
        istoricBackButton.addActionListener(this::istoricBackButtonActionPerformed);
        istoricBackButtonPanel.add(istoricBackButton);

        istoricMedicalPanou.add(istoricBackButtonPanel, java.awt.BorderLayout.NORTH);

        adaugaIstoricButton.setText("Adauga");
        adaugaIstoricButton.setPreferredSize(new java.awt.Dimension(80, 27));
        adaugaIstoricButton.addActionListener(this::adaugaIstoricButtonActionPerformed);
        istoricButtonPanel.add(adaugaIstoricButton);

        editeazaIstoricButton.setText("Editeaza");
        editeazaIstoricButton.addActionListener(this::editeazaIstoricButtonActionPerformed);
        istoricButtonPanel.add(editeazaIstoricButton);

        stergeIstoricButton.setText("Sterge");
        stergeIstoricButton.setPreferredSize(new java.awt.Dimension(80, 27));
        stergeIstoricButton.addActionListener(this::stergeIstoricButtonActionPerformed);
        istoricButtonPanel.add(stergeIstoricButton);

        istoricMedicalPanou.add(istoricButtonPanel, java.awt.BorderLayout.SOUTH);

        istoricBigPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 150));

        TabelIstoricMedical.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Data", "Motiv"
            }
        ));
        TabelIstoricMedical.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelIstoricMedical.setPreferredSize(new java.awt.Dimension(250, 100));
        jScrollPane1.setViewportView(TabelIstoricMedical);
        if (TabelIstoricMedical.getColumnModel().getColumnCount() > 0) {
            TabelIstoricMedical.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        istoricTabelPanel.add(jScrollPane1);

        istoricBigPanel.add(istoricTabelPanel, java.awt.BorderLayout.PAGE_START);

        istoricDetaliiPanel.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Data:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        istoricDetaliiPanel.add(jLabel2, gridBagConstraints);

        dataIstoricLabel.setText("jLabel3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        istoricDetaliiPanel.add(dataIstoricLabel, gridBagConstraints);

        jLabel5.setText("Medic:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        istoricDetaliiPanel.add(jLabel5, gridBagConstraints);

        medicIstoricLabel.setText("jLabel7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        istoricDetaliiPanel.add(medicIstoricLabel, gridBagConstraints);

        jLabel9.setText("Diagnostic:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        istoricDetaliiPanel.add(jLabel9, gridBagConstraints);

        jLabel11.setText("Tratament:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        istoricDetaliiPanel.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        istoricDetaliiPanel.add(diagnosticIstoricTextField, gridBagConstraints);

        tratamentIstoricTextArea.setEditable(false);
        tratamentIstoricTextArea.setColumns(20);
        tratamentIstoricTextArea.setLineWrap(true);
        tratamentIstoricTextArea.setRows(5);
        tratamentIstoricTextArea.setWrapStyleWord(true);
        tratamentIstoricTextArea.setPreferredSize(new java.awt.Dimension(150, 80));
        jScrollPane5.setViewportView(tratamentIstoricTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        istoricDetaliiPanel.add(jScrollPane5, gridBagConstraints);

        istoricBigPanel.add(istoricDetaliiPanel, java.awt.BorderLayout.CENTER);

        istoricMedicalPanou.add(istoricBigPanel, java.awt.BorderLayout.CENTER);

        containerDinamic.add(istoricMedicalPanou, "cardIstoricMedical");

        splitPaneCentral.setRightComponent(containerDinamic);

        getContentPane().add(splitPaneCentral, java.awt.BorderLayout.CENTER);

        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        delogareButton.setText("Log out");
        delogareButton.addActionListener(this::delogareButtonActionPerformed);
        buttonsPanel.add(delogareButton);
        buttonsPanel.add(filler1);

        adaugaAnimalButton.setText("Adauga");
        adaugaAnimalButton.addActionListener(this::adaugaAnimalButtonActionPerformed);
        buttonsPanel.add(adaugaAnimalButton);

        editareAnimalButton.setText("Editare");
        editareAnimalButton.addActionListener(this::editareAnimalButtonActionPerformed);
        buttonsPanel.add(editareAnimalButton);

        stergeAnimalButton.setText("Sterge");
        stergeAnimalButton.addActionListener(this::stergeAnimalButtonActionPerformed);
        buttonsPanel.add(stergeAnimalButton);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Schimbarea panoului daca se apasa butonul de Hrana
     * @param evt Eveniment
     */
    private void hranaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hranaButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        
        if (randSelectat == -1){
            return;
        }
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        
        if (oreIntroduse != null){
            oreIntroduse.clear();
        }
        
        incarcareDateHrana(animalSelectat.getID());
        CardLayout cardLayout = (CardLayout) containerDinamic.getLayout();
        cardLayout.show(containerDinamic, "cardHrana");
    }//GEN-LAST:event_hranaButtonActionPerformed

    /**
     * Functionalitatea stergerii unui animal din tabel.
     * Se foloseste {@code animaleDao} pentru stergerea din baza de date.
     * @param evt Eveniment
     */
    private void stergeAnimalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergeAnimalButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        if (randSelectat == -1){
            return;
        }
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalDeEliminat = tabelAnimaleModel.getAnimal(modelRow);

        int confirmare = JOptionPane.showConfirmDialog(this, "Esti sigur ca doresti sa stergi animalul " + animalDeEliminat.getNume() + "?", "Confirmare", JOptionPane.YES_NO_OPTION);  
        if (confirmare == JOptionPane.YES_OPTION){
            boolean succes = animaleDao.stergereAnimal(animalDeEliminat.getID());
            
            if (succes){
                incarcareDate();
                JOptionPane.showMessageDialog(this, "Animalul '" + animalDeEliminat.getNume() + "' a fost sters cu succes");
                return;
            }
            else {
                JOptionPane.showConfirmDialog(this, "Animalul nu a putut fi sters", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_stergeAnimalButtonActionPerformed

    /**
     * Functionalitatea adaugarii unui animal din tabel.
     * Se trimite obiectul de adaugat catre {@link AdaugaAnimalForm}.
     * @param evt 
     */
    private void adaugaAnimalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaAnimalButtonActionPerformed
        AdaugaAnimalForm adaugareForm = new AdaugaAnimalForm(this);
        adaugareForm.pack();
        adaugareForm.setLocationRelativeTo(this);
        adaugareForm.setVisible(true);
        incarcareDate();
    }//GEN-LAST:event_adaugaAnimalButtonActionPerformed

    /**
     * Functionalitatea editarii unui animal din tabel.
     * Se trimite obiectul de editat catre {@link EditareAnimalForm}.
     * @param evt 
     */
    private void editareAnimalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editareAnimalButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        
        if (randSelectat < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza un animal intai!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalDeEditat = tabelAnimaleModel.getAnimal(randSelectat);
        
        EditareAnimalForm editareForm = new EditareAnimalForm(this, animalDeEditat);
        editareForm.pack();
        editareForm.setLocationRelativeTo(this);
        editareForm.setVisible(true);
        incarcareDate();
    }//GEN-LAST:event_editareAnimalButtonActionPerformed

    /**
     * Schimbarea panoului daca se apasa butonul de Vaccinuri
     * @param evt Eveniment
     */
    private void vaccinuriButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaccinuriButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        
        
        if (randSelectat < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza un animal intai!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        CardLayout cardLayout = (CardLayout) containerDinamic.getLayout();
        cardLayout.show(containerDinamic, "cardVaccinuri");
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        incarcareDateVaccinuri(animalSelectat.getID());
        TabelVaccinuri.setRowHeight(25);
    }//GEN-LAST:event_vaccinuriButtonActionPerformed

    /**
     * Revenirea la panoul principal de detalii
     * @param evt 
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        CardLayout cardLayout = (CardLayout) containerDinamic.getLayout();
        cardLayout.show(containerDinamic, "cardDetalii");
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Functionalitatea editarii unui vaccin din tabelul de vaccinuri.
     * Se trimite obiectul de editat catre {@link EditareVaccinForm}.
     * @param evt 
     */
    private void editareVaccinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editareVaccinButtonActionPerformed
        int randSelectat = TabelVaccinuri.getSelectedRow();
        if (randSelectat < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza un vaccin intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int modelRow = TabelVaccinuri.convertRowIndexToModel(randSelectat);
        Vaccin vaccinDeEditat = tabelVaccinModel.getVaccin(modelRow);
        
        EditareVaccinForm editareVaccinForm = new EditareVaccinForm(this, vaccinDeEditat);
        editareVaccinForm.setLocationRelativeTo(this);
        editareVaccinForm.pack();
        editareVaccinForm.setVisible(true);
        System.out.println("Refresh pentru animalul ID: " + vaccinDeEditat.getID_Animal());
    }//GEN-LAST:event_editareVaccinButtonActionPerformed

    /**
     * Functionalitatea adaugarii unui vaccin din tabelul de vaccinuri.
     * Se trimite obiectul de editat catre {@link AdaugaVaccinForm}.
     * @param evt 
     */
    private void adaugaVaccinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaVaccinButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        if (randSelectat == -1){
            return;
        }
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        
        AdaugaVaccinForm vaccinForm = new AdaugaVaccinForm(this, true, animalSelectat.getID());
        vaccinForm.setLocationRelativeTo(this);
        vaccinForm.pack();
        vaccinForm.setVisible(true);
        incarcareDateVaccinuri(animalSelectat.getID());
    }//GEN-LAST:event_adaugaVaccinButtonActionPerformed

    /**
     * Functionalitatea stergerii unui vaccin din tabelul de vaccinuri.
     * Se foloseste {@code vaccinuriDao} pentru stergerea din baza de date.
     * @param evt 
     */
    private void stergereVaccinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergereVaccinButtonActionPerformed
        int randSelectat = TabelVaccinuri.getSelectedRow();
        
        if (randSelectat < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza un vaccin intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int modelRow = TabelVaccinuri.convertRowIndexToModel(randSelectat);
        Vaccin vaccinDeEliminat = tabelVaccinModel.getVaccin(modelRow);
        boolean succes = tabelVaccinModel.vaccinuri.remove(vaccinDeEliminat);
        vaccinuriDao.stergereVaccin(vaccinDeEliminat.getID_Animal());
        tabelVaccinModel.fireTableDataChanged();
        
        if (succes){
            JOptionPane.showMessageDialog(this, "Vaccinul a fost eliminat cu succes!");
            return;
        }
        else {
            JOptionPane.showMessageDialog(this, "Vaccinul nu a putut fi sters!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_stergereVaccinButtonActionPerformed

    /**
     * Revenirea la panoul principal de detalii
     * @param evt 
     */
    private void backHranaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backHranaButtonActionPerformed
        CardLayout cardLayout = (CardLayout) containerDinamic.getLayout();
        cardLayout.show(containerDinamic, "cardDetalii");
    }//GEN-LAST:event_backHranaButtonActionPerformed

    /**
     * Functionalitatea stergerii unei ore din tabelul de ore alocat Hranei.
     * Se foloseste {@code hranaDao} pentru stergerea din baza de date.
     * @param evt 
     */
    private void stergereOraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergereOraButtonActionPerformed
        int indexOraSelectat = listaOre.getSelectedIndex();
        
        if (indexOraSelectat < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza o ora intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else {
            oreIntroduse.remove(indexOraSelectat);
            int randAnimal = TabelAnimale.getSelectedRow();
            int modelRow = TabelAnimale.convertRowIndexToModel(randAnimal);
            Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
            Hrana hranaAnimalSelectat = hranaDao.getHranaPerAnimal(animalSelectat.getID());
            
            if (hranaAnimalSelectat != null){
                hranaAnimalSelectat.setOreAdministrare(extragereOre());
                hranaDao.updateHrana(hranaAnimalSelectat);
            }
        }
    }//GEN-LAST:event_stergereOraButtonActionPerformed

    /**
     * Functionalitatea adaugarii unei ore in tabelul de ore alocat Hranei.
     * Se foloseste {@code hranaDao} pentru adaugare in baza de date.
     * @param evt 
     */
    private void adaugareOraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugareOraButtonActionPerformed
        JSpinner timpSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor oraEditor = new JSpinner.DateEditor(timpSpinner, "HH:mm");
        timpSpinner.setEditor(oraEditor);
        timpSpinner.setValue(new Date());
        
        int randSelectat = TabelAnimale.getSelectedRow();
        if (randSelectat == -1){
            return;
        }
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        
        int input = JOptionPane.showConfirmDialog(this, timpSpinner, "Selecteaza o ora", JOptionPane.OK_CANCEL_OPTION);
        
        if (input == JOptionPane.OK_OPTION){
            try {
                SimpleDateFormat oraFormat = new SimpleDateFormat("HH:mm");
                String ora = oraFormat.format(timpSpinner.getValue());
                
                if (!oreIntroduse.contains(ora)){
                    oreIntroduse.addElement(ora);
                    sortareOre();
                }
                
                Hrana hranaCurenta = hranaDao.getHranaPerAnimal(animalSelectat.getID());
                if (hranaCurenta != null){
                    hranaCurenta.setOreAdministrare(extragereOre());
                    hranaDao.updateHrana(hranaCurenta);
                    System.out.println("Au fost salvate orele!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Introdu o ora valida!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_adaugareOraButtonActionPerformed

    private void tipHranaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipHranaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipHranaTextFieldActionPerformed

    private void nr_ziHranaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nr_ziHranaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nr_ziHranaTextFieldActionPerformed

    /**
     * Functionalitatea crearii / editarii hranei.
     * In functie de existenta unui obicte de tip {@link Hrana}, campurile de text devin
     * editabile sau nu.
     * Se foloseste {@code hranaDao} pentru creeare / editare in baza de date.
     * @param evt 
     */
    private void creeareEditareHranaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creeareEditareHranaButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        if (randSelectat == -1){
            return;
        }
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        
        if (creeareEditareHranaButton.getText().equals("Salveaza")){
            String nume = numeHranaTextField.getText().trim();
            String tip = tipHranaTextField.getText().trim();
            String cantitate = cantitateHranaTextField.getText().trim();
            String portie = nr_ziHranaTextField.getText().trim();
            ArrayList<String> oreExtrase = extragereOre();

            if (nume.isEmpty() || tip.isEmpty() || cantitate.isEmpty() || portie.isEmpty()){
                JOptionPane.showMessageDialog(this, "Toate campurile sunt obligatorii", "Validare", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!validareSiCuloareTextField(cantitateHranaTextField) || !validareSiCuloareTextField(nr_ziHranaTextField)){
                JOptionPane.showMessageDialog(this, "Corecteaza erorile marcate intai!", "Eroare validare", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Double cantitateDouble = Double.parseDouble(cantitate);
            Integer portieInt = Integer.parseInt(portie);

            if (cantitateDouble <= 0.0 || cantitateDouble > 100){
                JOptionPane.showMessageDialog(this, "Cantitatea trebuie sa se afle in intervalul [1, 100]", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (portieInt <= 0 || portieInt > 7){
                JOptionPane.showMessageDialog(this, "Nr/zi trebuie sa se afle in intervalul [1, 7]", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Hrana hranaNoua = new Hrana(nume, tip, cantitateDouble, portieInt, animalSelectat.getID());
            hranaNoua.setOreAdministrare(oreExtrase);
            Hrana existaHranaID = hranaDao.getHranaPerAnimal(animalSelectat.getID());
            boolean succes;
            
            if (existaHranaID != null){
                existaHranaID.setOreAdministrare(oreExtrase);
                succes = hranaDao.updateHrana(existaHranaID);
            }
            else {
                succes = hranaDao.creeareHrana(hranaNoua);
            }

            if (succes){
                JOptionPane.showMessageDialog(this, "Hrana a fost adaugata cu succes!");
                return;
            }
            else {
                JOptionPane.showMessageDialog(this, "Eroare la adaugarea hranei!", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else {
            Hrana hranaDeEditat = hranaDao.getHranaPerAnimal(animalSelectat.getID());
            EditareHranaForm hranaForm = new EditareHranaForm(this, hranaDeEditat, animalSelectat.getID());
            hranaForm.pack();
            hranaForm.setLocationRelativeTo(this);
            hranaForm.setVisible(true);
        }
    }//GEN-LAST:event_creeareEditareHranaButtonActionPerformed

    /**
     * Functionalitatea stergerii hranei din tabelul hranei.
     * Se foloseste {@code hranaDao} pentru stergerea din baza de date.
     * @param evt 
     */
    private void stergeHranaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergeHranaButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        if (randSelectat == -1){
            return;
        }
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        
        int confirmare = JOptionPane.showConfirmDialog(this, "Esti sigur ca vrei sa stergi hrana pentru animalul " + animalSelectat.getNume() + "?", "Confirmare", JOptionPane.YES_NO_OPTION);
        if (confirmare == JOptionPane.YES_OPTION){
            boolean succes = hranaDao.stergereHrana(animalSelectat.getID());
            
            if (succes){
                 nr_ziHranaTextField.setFocusable(true);
                nr_ziHranaTextField.setEditable(true);
                nr_ziHranaTextField.setText("");

                numeHranaTextField.setFocusable(true);
                numeHranaTextField.setEditable(true);
                numeHranaTextField.setText("");

                cantitateHranaTextField.setFocusable(true);
                cantitateHranaTextField.setEditable(true);
                cantitateHranaTextField.setText("");

                tipHranaTextField.setFocusable(true);
                tipHranaTextField.setEditable(true);
                tipHranaTextField.setText("");

                String textFromFieldForColor = tipHranaTextField.getText().trim();
                cantitateHranaTextField.setBackground(Color.getColor(textFromFieldForColor));
                nr_ziHranaTextField.setBackground(Color.getColor(textFromFieldForColor));
                
                oreIntroduse.clear();
                creeareEditareHranaButton.setText("Salveaza");
                JOptionPane.showMessageDialog(this, "Hrana a fost stearsa cu succes!");
                return;
            }
            else {
                JOptionPane.showMessageDialog(this, "Eroare la stergerea hranei!", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_stergeHranaButtonActionPerformed

    /**
     * Schimbarea panoului daca se apasa butonul de Fisa Medicala
     * @param evt Eveniment
     */
    private void fisaMedicalaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fisaMedicalaButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow();
        if (randSelectat == -1){
            return;
        }
        
        if (randSelectat < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza un animal intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        TabelIstoricMedical.getSelectionModel().clearSelection();
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        incarcareDateIstoricMedical(animalSelectat.getID());

        diagnosticIstoricTextField.setText("");
        tratamentIstoricTextArea.setText("");
        medicIstoricLabel.setText("");
        dataIstoricLabel.setText("");

        CardLayout cardLayour = (CardLayout) containerDinamic.getLayout();
        cardLayour.show(containerDinamic, "cardIstoricMedical");
        
    }//GEN-LAST:event_fisaMedicalaButtonActionPerformed

    /**
     * Revenirea la panoul principal de datalii
     * @param evt Eveniment
     */
    private void istoricBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_istoricBackButtonActionPerformed
        CardLayout cardLayout = (CardLayout) containerDinamic.getLayout();
        cardLayout.show(containerDinamic, "cardDetalii");
    }//GEN-LAST:event_istoricBackButtonActionPerformed

    /**
     * Functionalitatea stergerii unei inregistrari medicale din tabelul istoricului medical.
     * Se foloseste {@code istoricMedicalDao} pentru stergerea din baza de date.
     * @param evt 
     */
    private void stergeIstoricButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergeIstoricButtonActionPerformed
        int randIstoric = TabelIstoricMedical.getSelectedRow();
            
        if (randIstoric < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza o inregistrare intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //TabelIstoricMedical.clearSelection();

        int modelRow = TabelIstoricMedical.convertRowIndexToModel(randIstoric);
        IstoricMedical istoricCurent = tabelIstoricMedicalModel.getIstoricMedical(modelRow);
        
        if (istoricCurent != null){
            int confirmare = JOptionPane.showConfirmDialog(this, "Esti sigur ca doresti sa stergi inregistrarea?", "Confirmare", JOptionPane.YES_NO_OPTION);  
            if (confirmare == JOptionPane.YES_OPTION){
                boolean succes = istoricMedicalDao.stergereIstoricMedical(istoricCurent.getID_istoricMedical());
            
                if (succes){
                    int randAnimale = TabelAnimale.getSelectedRow();
                    int modelRowAnimale = TabelAnimale.convertRowIndexToModel(randAnimale);
                    Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRowAnimale);
                    incarcareDateIstoricMedical(animalSelectat.getID());
                    
                    JOptionPane.showMessageDialog(this, "Inregistrarea fost sters cu succes");
                    return;
                }
                else {
                    JOptionPane.showConfirmDialog(this, "Inregistrarea nu a putut fi sters", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    }//GEN-LAST:event_stergeIstoricButtonActionPerformed

    /**
     * Functionalitatea adaugarii unei inregistrari medicale in tabelul de istoric medical.
     * Se trimite obiectul de editat catre {@link AdaugaIstoricMedicalForm}.
     * @param evt 
     */
    private void adaugaIstoricButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaIstoricButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow(); 
        if (randSelectat < 0){
            JOptionPane.showMessageDialog(this, "Selecteaza o inregistrare intai!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int modelRow = TabelAnimale.convertRowIndexToModel(randSelectat);
        Animal animalSelectat = tabelAnimaleModel.getAnimal(modelRow);
        
        AdaugaIstoricMedicalForm adaugareIstoric = new AdaugaIstoricMedicalForm(this, animalSelectat.getID());
        adaugareIstoric.pack();
        adaugareIstoric.setLocationRelativeTo(this);
        adaugareIstoric.setVisible(true);
        incarcareDateIstoricMedical(animalSelectat.getID());
    }//GEN-LAST:event_adaugaIstoricButtonActionPerformed

    /**
     * Functionalitatea editarii unei inregistrari medicale in tabelul de istoric medical.
     * Se trimite obiectul de editat catre {@link EditareIstoricMedicalForm}.
     * @param evt 
     */
    private void editeazaIstoricButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editeazaIstoricButtonActionPerformed
        int randSelectat = TabelAnimale.getSelectedRow(); 
        int randIstoric = TabelIstoricMedical.getSelectedRow();
        if (randIstoric < 0){
            return;
        }
        
        int modelRow = TabelIstoricMedical.convertRowIndexToModel(randIstoric);
        IstoricMedical istoricDeEditat = tabelIstoricMedicalModel.getIstoricMedical(modelRow);
        
        EditareIstoricMedicalForm editareIstoric = new EditareIstoricMedicalForm(this, istoricDeEditat);
        editareIstoric.pack();
        editareIstoric.setLocationRelativeTo(this);
        editareIstoric.setVisible(true);
    }//GEN-LAST:event_editeazaIstoricButtonActionPerformed

    /**
     * Gestionare proces delogare
     * Redeschide fereastra {@link LoginView}
     * @param evt Eveniment
     */
    private void delogareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delogareButtonActionPerformed
        int confirmare = JOptionPane.showConfirmDialog(this, "Esti sigur ca vrei sa iesi din cont?", "Logout", JOptionPane.YES_NO_OPTION);
        
        if (confirmare == JOptionPane.YES_OPTION){
            DateUtilizator.delogare();
            this.dispose();
            
            java.awt.EventQueue.invokeLater(() -> {
                LoginView loginView = new LoginView();
                UsersDao userDao = new UsersDao();
                new Login(loginView, userDao);
                loginView.setVisible(true);
            });
        }
    }//GEN-LAST:event_delogareButtonActionPerformed

    /**
     * Sorteaza alfabetic orele de administrare afisate in lista
     */
    public void sortareOre(){
        ArrayList<String> arrayMomentan = new ArrayList<>();
        for (int i = 0; i < oreIntroduse.size(); i++){
            arrayMomentan.add(oreIntroduse.get(i));
        }
        
        Collections.sort(arrayMomentan);
        oreIntroduse.clear();
        for (String ora : arrayMomentan){
            oreIntroduse.addElement(ora);
        }
    }
    
    
    //pt ca JList foloseste DefaultListModel, trebe extrasa informatia din lista si pusa intr un array normal
    /**
     * Se extrag orele de administrare din modelul liste Jlist
     * @return Un {@code ArrayList} de string-uri in format "10:05"
     */
    public ArrayList<String> extragereOre(){
        ArrayList<String> arrayMomentan = new ArrayList<>();
        for (int i = 0; i < oreIntroduse.size(); i++){
            arrayMomentan.add(oreIntroduse.getElementAt(i));
        }
        
        return arrayMomentan;
    }

    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { // Nimbus e cel care face butoanele rotunde/moderne
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LoginView ecranPrincipal = new LoginView();
        UsersDao dao = new UsersDao();
        Login login = new Login(ecranPrincipal, dao);
        ecranPrincipal.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelAnimale;
    private javax.swing.JTable TabelIstoricMedical;
    private javax.swing.JTable TabelVaccinuri;
    private javax.swing.JButton adaugaAnimalButton;
    private javax.swing.JButton adaugaIstoricButton;
    private javax.swing.JButton adaugaVaccinButton;
    private javax.swing.JButton adaugareOraButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton backHranaButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField cantitateHranaTextField;
    private javax.swing.JPanel containerDinamic;
    private javax.swing.JButton creeareEditareHranaButton;
    private javax.swing.JLabel dataIstoricLabel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton delogareButton;
    private javax.swing.JPanel detaliiPanou;
    private javax.swing.JTextField diagnosticIstoricTextField;
    private javax.swing.JButton editareAnimalButton;
    private javax.swing.JButton editareVaccinButton;
    private javax.swing.JButton editeazaIstoricButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton fisaMedicalaButton;
    private javax.swing.JLabel genLabel;
    private javax.swing.JLabel genLabelInfo;
    private javax.swing.JLabel greutateLabel;
    private javax.swing.JLabel greutateLabelInfo;
    private javax.swing.JPanel hranaBackButtonPanel;
    private javax.swing.JButton hranaButton;
    private javax.swing.JPanel hranaButtonsPanel;
    private javax.swing.JPanel hranaPanou;
    private javax.swing.JButton istoricBackButton;
    private javax.swing.JPanel istoricBackButtonPanel;
    private javax.swing.JPanel istoricBigPanel;
    private javax.swing.JPanel istoricButtonPanel;
    private javax.swing.JPanel istoricDetaliiPanel;
    private javax.swing.JPanel istoricMedicalPanou;
    private javax.swing.JPanel istoricTabelPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> listaOre;
    private javax.swing.JLabel medicIstoricLabel;
    private javax.swing.JTextField nr_ziHranaTextField;
    private javax.swing.JTextField numeHranaTextField;
    private javax.swing.JLabel numeLabel;
    private javax.swing.JPanel oreAdministrarePanel;
    private javax.swing.JScrollPane oreAdministrareScrollPane;
    private javax.swing.JLabel rasaLabel;
    private javax.swing.JLabel rasaLabelInfo;
    private javax.swing.JSplitPane splitPaneCentral;
    private javax.swing.JButton stergeAnimalButton;
    private javax.swing.JButton stergeHranaButton;
    private javax.swing.JButton stergeIstoricButton;
    private javax.swing.JButton stergereOraButton;
    private javax.swing.JButton stergereVaccinButton;
    private javax.swing.JPanel tabelPanel;
    private javax.swing.JTextField tipHranaTextField;
    private javax.swing.JTextArea tratamentIstoricTextArea;
    private javax.swing.JPanel vaccinuriBackButtonPanel;
    private javax.swing.JButton vaccinuriButton;
    private javax.swing.JPanel vaccinuriButtonsPanel;
    private javax.swing.JPanel vaccinuriPanou;
    // End of variables declaration//GEN-END:variables
}
