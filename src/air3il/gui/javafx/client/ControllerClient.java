package air3il.gui.javafx.client;

import air3il.commun.dto.DtoClient;
import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsClient;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;

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
    @FXML
    Button Supprimer;
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

            modelClient.ValiderMiseAJour();
            modelClient.ajouter();
            initChamp();
            managerGui.showView(EnumView.Client);
            //System.out.println("ok pour doAjouter");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("   Insertion reussite !! ");
            alert.show();
        } catch (Exception e) {
            managerGui.afficherErreur(e);
        }
    }

    @FXML
    private void doSuivant() {

        managerGui.showView(EnumView.Reservation);
    }

    @FXML
    private void doRechercher() {
        managerGui.showView(EnumView.Reservation);
    }

    @FXML
    private void domodifier() {
        modelClient.preparerModifier(tableViewClient.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void doSupprimer() {
        int index = tableViewClient.getSelectionModel().getFocusedIndex();

        if (index != -1) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Voulez vous vraiment supprimer ?");
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

        tableViewClient.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

            }
        });
        columnNom.setCellValueFactory(t -> t.getValue().getPropNom());
        columnNom.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNom.setOnEditCommit(new EventHandler<CellEditEvent<ObsClient, String>>() {
            @Override
            public void handle(CellEditEvent<ObsClient, String> t) {
              t.getRowValue().getPropNom().set(t.getNewValue());
              modelClient.modifier(t.getRowValue());
            }
        });
        columnPrenom.setCellValueFactory(t -> t.getValue().getPropPrenom());
        columnPrenom.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPrenom.setOnEditCommit(new EventHandler<CellEditEvent<ObsClient, String>>() {
            @Override
            public void handle(CellEditEvent<ObsClient, String> t) {
              t.getRowValue().getPropPrenom().set(t.getNewValue());
              modelClient.modifier(t.getRowValue());
            }
        });
        columnTel.setCellValueFactory(t -> t.getValue().getPropTel());
        columnTel.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTel.setOnEditCommit(new EventHandler<CellEditEvent<ObsClient, String>>() {
            @Override
            public void handle(CellEditEvent<ObsClient, String> t) {
              t.getRowValue().getPropTel().set(t.getNewValue());
              modelClient.modifier(t.getRowValue());
            }
        });
        columnEmail.setCellValueFactory(t -> t.getValue().getPropEmail());
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setOnEditCommit(new EventHandler<CellEditEvent<ObsClient, String>>() {
            @Override
            public void handle(CellEditEvent<ObsClient, String> t) {
              t.getRowValue().getPropEmail().set(t.getNewValue());
              modelClient.modifier(t.getRowValue());
            }
        });

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
