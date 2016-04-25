package air3il.gui.javafx.reservation;

import air3il.gui.javafx.EditingCell;
import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsReservation;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<ObsReservation> tableViewPassagers;
    @FXML
    private TableColumn<ObsReservation, String> columnNom;
    @FXML
    private TableColumn<ObsReservation, String> columnPrenom;
    @FXML
    private TableColumn<ObsReservation, String> columnTel;
    @FXML
    private TableColumn<ObsReservation, String> columnEmail;

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
            System.out.println("ok pour doAjouter");
        } catch (Exception e) {
            managerGui.afficherErreur(e);
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

        tableViewPassagers.getSelectionModel().getSelectedItems().addListener(
                (ListChangeListener<ObsReservation>) (c) -> {
                    if (tableViewPassagers.getSelectionModel().getSelectedItem() == null) {
                        Ajouter.setDisable(true);

                    } else {
                        System.out.println("tableViewPassagers non vide");
                    }
                });

        tableViewPassagers.setItems(modelReservation.getObsListReservations());

        columnNom.setCellValueFactory(t -> t.getValue().getPropNom());
        columnPrenom.setCellValueFactory(t -> t.getValue().getPropPrenom());
        columnTel.setCellValueFactory(t -> t.getValue().getPropTel());
        columnEmail.setCellValueFactory(t -> t.getValue().getPropEmail());

        columnNom.setCellFactory(p -> new EditingCell<>());
        columnPrenom.setCellFactory(p -> new EditingCell<>());
        columnTel.setCellFactory(p -> new EditingCell<>());
        columnEmail.setCellFactory(p -> new EditingCell<>());
    }

    public void initChamp() {
        Nom.setText("");
        Prenom.setText(" ");
        Tel.setText("");
        Email.setText("");
    }

    // Gestion des évènements
    // Clic sur la liste
    /*@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				doModifier();
			}
		}
	}*/
}
