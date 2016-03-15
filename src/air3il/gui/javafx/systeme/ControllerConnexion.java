package air3il.gui.javafx.systeme;

import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsCompte;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerConnexion implements IControllerJavaFx {

    // Champs
    private ManagerGui managerGui;
    private ModelSysteme modelSysteme;

    // Composants de la vue
    @FXML
    private TextField fieldPseudo;
    @FXML
    private PasswordField fieldMotDePasse;

    // Actions
    @FXML
    public void doConnexion() {
        try {
            modelSysteme.ouvrirSessionUtilisateur();
            modelSysteme.getPropTitre().set("Bienvenue");
            modelSysteme.getPropMessage().set("Connexion réussie");
            managerGui.showView(EnumView.Message);
        } catch (Exception e) {
            managerGui.afficherErreur(e);
        }
    }

    // Initialisation du Controller
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {

        // Injection  de dépendances
        this.managerGui = managerGui;
        modelSysteme = managerGui.getModel(ModelSysteme.class);

        // Data binding
        ObsCompte compteVue = modelSysteme.getCompteVue();
        fieldPseudo.textProperty().bindBidirectional(compteVue.getPropPseudo());
        fieldMotDePasse.textProperty().bindBidirectional(compteVue.getPropMotDePasse());
    }

}
