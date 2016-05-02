package air3il.gui.javafx.reservation;

import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsReservation;
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
 * @author ARSENE
 */
public class ControllerReservation implements IControllerJavaFx {

    // composants de la vue
    @FXML
    TextField Nom;
    @FXML
    TextField Prenom;
    @FXML
    TextField Tel;
    @FXML
    TextField Email;
    @FXML
    Button Ajouter;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private TableView<ObsReservation> tableViewPassagers;
    @FXML
    private TableColumn<ObsReservation, String> columnNom;
    @FXML
    private TableColumn<ObsReservation, String> columnPrenom;
    @FXML
    private TableColumn<ObsReservation, String> columnTel;
    @FXML
    private TableColumn<ObsReservation, String> columnEmail;
    boolean click;

    // Champs
    private ManagerGui managerGui;
    private ModelReservation modelReservation;

    @FXML
    private void doAjouter() {
          
        try {
            modelReservation.preparerAjouter();
            modelReservation.ValiderMiseAJour();
            initChamp();
            managerGui.showView(EnumView.Reservation);
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
        int index = tableViewPassagers.getSelectionModel().getFocusedIndex();

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
                    modelReservation.getObsListReservations().remove(index);

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

        // Injection de dépendances
        this.managerGui = managerGui;
        modelReservation = managerGui.getModel(ModelReservation.class);

        // Data binding
        ObsReservation reservationVue = modelReservation.getReservationVue();
        Nom.textProperty().bindBidirectional(reservationVue.getPropNom());
        Prenom.textProperty().bindBidirectional(reservationVue.getPropPrenom());
        Email.textProperty().bindBidirectional(reservationVue.getPropEmail());
        Tel.textProperty().bindBidirectional(reservationVue.getPropTel());
        tableViewPassagers.setItems(modelReservation.getObsListReservations());

        tableViewPassagers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

            }
        });

        tableViewPassagers.setItems(modelReservation.getObsListReservations());

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
