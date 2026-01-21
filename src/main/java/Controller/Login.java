package Controller;
import dao.UsersDao;
import View.LoginView;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DateUtilizator;

/**
 * Gestioneaza fluxul de autentificare prin legatura dintre {@link LoginView} si {@link UsersDao}.
 * Realizeaza tranzitia spre ecranul principal.
 * @author oana
 */

public class Login {
    LoginView ecran;
    UsersDao dao;
    
    /**
     * Constructorul clasei Login
     * @param ecran Instanta ferestre de login
     * @param dao Instanta obiectului ce ofera accesul la datele utilizatorilor
     */
    public Login (LoginView ecran, UsersDao dao){
        this.ecran = ecran;
        this.dao = dao;
        schimbareEcraneDupaRoluri();
    }
    
    /**
     * Configurarea Listener-ului pentru butonul Login.
     * Metoda preia datele din interfata si le stocheaza global intr-un obiect {@link DateUtilizator}, apoi deschide fereastra principala a aplicatiei.
     */
    public void schimbareEcraneDupaRoluri(){
        this.ecran.setLoginListener(new ActionListener() {
            
            /**
             * Actiune executata la declansarea butonului Login
             * @param e Eveniment ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = ecran.getUsername();
                String password = ecran.getPassword();
                
                DateUtilizator utilizatorGasit = dao.getUser(user, password);
                
                if (utilizatorGasit != null){
                    ecran.showMessage("Autentificare reusita!");
                    ecran.dispose();
                    String rol = utilizatorGasit.getRol();
                    Integer ID_User = utilizatorGasit.getID_User();
                
                    DateUtilizator.setDateUtilizator(ID_User, user, rol);
                    
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                }
                else {
                    ecran.showMessage("Eroare! Username sau parola gresita!");
                }
            }
        });
    }
}
