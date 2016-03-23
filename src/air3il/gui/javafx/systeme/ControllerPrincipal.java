package air3il.gui.javafx.systeme;

import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.ManagerGui;
import air3il.commun.securite.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class ControllerPrincipal implements IControllerJavaFx {

    // Champ
    private ManagerGui managerGui;
    private ModelSysteme modelSysteme;

    // Composants de la vue
    @FXML
    private HBox menuBar;

    // Actions
    @FXML
    public void doSeDeconnecter() {
        try {
            modelSysteme.fermerSessionUtilisateur();
            configurerMenu();
            managerGui.showView(EnumView.Connexion);
        } catch (Exception e) {
            managerGui.afficherErreur(e);
        }
    }

    @FXML
    public void doQuitter() {
        managerGui.getStagePrincipal().close();
    }
    @FXML
    public void doAfficherVols(){
        managerGui.showView(EnumView.Vols);
        
    }
    @FXML
    public void doAfficherReservation(){
        managerGui.showView(EnumView.Reservation);
    }

    // Initialisation du Controller
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {

        // Injection  de dépendances
        this.managerGui = managerGui;
        modelSysteme = managerGui.getModel(ModelSysteme.class);

        // Le changement du compte connecté modifie automatiquement le menu
        modelSysteme.getPropCompteConnecte().addListener(
                (ov, oldValue, newValue) -> {
                    configurerMenu();
                }
        );
    }

    // Méthodes auxiliaires
    private void configurerMenu() {

        menuBar.setVisible(false);

        if (modelSysteme.getCompteConnecte() != null) {
            
            menuBar.setVisible(true);
        }
    }

}
