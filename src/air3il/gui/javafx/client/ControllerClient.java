
package air3il.gui.javafx.client;

import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsClient;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

/**
 *
 * @author Joel
 */
public class ControllerClient implements IControllerJavaFx {

    // composants de la vue
    @FXML
    TextField Nom;
    @FXML
    TextField Prenom;
    @FXML
    TextField Nom_recherche;
    @FXML
    TextField Prenom_recherche;
    @FXML
    TextField Tel;
    @FXML
    TextField Email;
    @FXML
    TextField Depart;
    @FXML
    TextField Arrivee;
    @FXML
    Button Ajouter;
    @FXML
    Button Suivant;
    @FXML
    Button Rechercher;
    //@FXML
    //private Button buttonSupprimer;
    @FXML
    private TableView<ObsClient> tableViewClient;
    @FXML
    private TableColumn<ObsClient, String> columnNom;
    @FXML
    private TableColumn<ObsClient, String> columnPrenom;
    @FXML
    private TableColumn<ObsClient, String> columnTel;
    @FXML
    private TableColumn<ObsClient, String> columnEmail;
    boolean click;

    // Champs
    private ManagerGui managerGui;
    private ModelClient modelClient;

    @FXML
    private void doAjouter() {
          
        try {
            modelClient.preparerAjouter();
            modelClient.ValiderMiseAJour();
            initChamp();
            managerGui.showView(EnumView.Client);
            //System.out.println("ok pour doAjouter");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("   Inséré !! ");
            alert.show();
        } catch (Exception e) {
            managerGui.afficherErreur(e);
        }
    }

    @FXML
    public void doSupprimer() {
        int index = tableViewClient.getSelectionModel().getFocusedIndex();

        if (index != -1) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("êtes vous sûre ?");
            ButtonType buttonTypeOUI = new ButtonType("OUI");
            ButtonType buttonTypeNON = new ButtonType("NON");
            alert.getButtonTypes().setAll(buttonTypeOUI, buttonTypeNON);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOUI) {
                // ... user chose "OUI"
                //index de la ligne
                try {
                    //suppression de la ligne selectionnée
                    modelClient.getObsListClient().remove(index);

                } catch (Exception e) {
                }
            } else if (result.get() == buttonTypeNON) {
                // ... user chose "NON"
            }

        }
    }

    // Initialisations
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {
        System.err.println("ici");
        // Injection de dépendances
        this.managerGui = managerGui;
        modelClient = managerGui.getModel(ModelClient.class);

        // Data binding
        ObsClient ViewClient = modelClient.getClientVue();
        Nom.textProperty().bindBidirectional(ViewClient.getPropNom());
        Prenom.textProperty().bindBidirectional(ViewClient.getPropPrenom());
        Email.textProperty().bindBidirectional(ViewClient.getPropEmail());
        Tel.textProperty().bindBidirectional(ViewClient.getPropTel());
        tableViewClient.setItems(modelClient.getObsListClient());
        
        tableViewClient.setItems(modelClient.getObsListClient());

        tableViewClient.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

            }
        });

        tableViewClient.setItems(modelClient.getObsListClient());

        columnNom.setCellValueFactory(t -> t.getValue().getPropNom());
        columnPrenom.setCellValueFactory(t -> t.getValue().getPropPrenom());
        columnTel.setCellValueFactory(t -> t.getValue().getPropTel());
        columnEmail.setCellValueFactory(t -> t.getValue().getPropEmail());
        

    }

    public void initChamp() {
        Nom.setText("");
        Prenom.setText(" ");
        Tel.setText("");
        Email.setText("");
        Nom_recherche.setText("");
        Prenom_recherche.setText("");
    }

    @FXML
    private void gererClicSurListe(javafx.scene.input.MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                //doSupp_Pass
            }
        }
    }

}
