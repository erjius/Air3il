package air3il.gui.javafx.reservation;

import air3il.commun.dto.DtoPlace;
import air3il.commun.dto.DtoReservation;
import air3il.gui.javafx.EnumModeVue;
import static air3il.gui.javafx.EnumModeVue.CREER;
import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsReservation;
import air3il.gui.javafx.vols.ModelVols;
import air3il.gui.javafx.vueavion.Avion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ARSENE
 */
public class ControllerReservation implements IControllerJavaFx {

    //composants de la vue
    @FXML
    TextField Nom;
    @FXML
    TextField Prenom;
    @FXML
    TextField Place;
    @FXML
    TextField recup_vol;
    @FXML
    ComboBox<String> Vol;
    @FXML
    Button B_place;
    @FXML
    Button B_Ok;
    @FXML
    Button B_ajouter;
    @FXML
    Button B_actualiser;
    @FXML
    Button B_modifier;
    @FXML
    Button B_supprimer;
    @FXML
    private TableView<ObsReservation> tableViewReservations;
    @FXML
    private TableColumn<ObsReservation, String> columnNom;
    @FXML
    private TableColumn<ObsReservation, String> columnPrenom;
    @FXML
    private TableColumn<ObsReservation, String> columnPlace;
    @FXML
    private TableColumn<ObsReservation, String> columnVol;

    private Avion avion;
    // Champs
    private ManagerGui managerGui;
    private ModelReservation modelReservation;
    private EnumModeVue modevue;
    private ModelVols modelVols;

    // Actions
    @FXML
    private void doAjouter() {

        try {
            modevue = CREER;
            modelReservation.ValiderMiseAJour(modevue);
            modelReservation.preparerAjouter();
            // System.out.println("Vol = "+recup_vol.getText());
            managerGui.showView(EnumView.Reservation);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(" Inséré !! ");
            alert.show();
        } catch (Exception e) {
            managerGui.afficherErreur(e);
        }
    }

    public void rempliCombo() {
        List<DtoReservation> listR = modelVols.getReservationsClients();
        if (false || listR.size() > 0) {
            listR.stream().forEach((dto) -> {
                Vol.getItems().add(String.valueOf(dto.getVol().getville_dep() + " - " + dto.getVol().getville_arr()));
            });
        }

    }

    @FXML
    public void doSeclectCbx(ActionEvent e) {
        recup_vol.setText(Vol.getValue());
    }

    @FXML
    private void gererClicSurListe(MouseEvent event) {
        int index = tableViewReservations.getSelectionModel().getFocusedIndex();

        if (index != -1) {
            //index de la ligne
            try {
                //suppression de la ligne selectionnÃ©e
                managerGui.showView(EnumView.Contact);

            } catch (Exception e) {

            }
        }

    }

    @FXML
    private void doActualiser() {
        try {
            modelReservation.actualiserListe();
        } catch (Exception e) {
            managerGui.afficherErreur(e);
        }
    }

    @FXML
    private void doModifier() {
        ObsReservation p = tableViewReservations.getSelectionModel().getSelectedItem();
        modelReservation.preparerModifier(p);

    }

    @FXML
    public void doAfficherAvion() {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setResizable(true);
        a.initOwner(managerGui.getStagePrincipal());
        a.setGraphic(null);
        a.setTitle("Choix de la place");
        a.getDialogPane().setContent(avion);
        a.getButtonTypes().add(new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE));
        a.getButtonTypes().add(new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE));
        Optional<ButtonType> btn = a.showAndWait();

        btn.ifPresent((ButtonType t) -> {
            if (t.getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
                // place choisie
                DtoPlace place = avion.getSelectionModel().getSelectedItem();
                if (place == null) {
                    return;
                }
                /*
                 * on gere la selection de place ici
                 */
            }
        });
    }

    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {

        // Injection de dépendances
        this.managerGui = managerGui;
        modelReservation = managerGui.getModel(ModelReservation.class);
        modelVols=managerGui.getModel(ModelVols.class);
        // Data binding 
        ObsReservation reservationVue = modelReservation.getReservationVue();
        Nom.textProperty().bindBidirectional(reservationVue.getPropNom());
        Prenom.textProperty().bindBidirectional(reservationVue.getPropPrenom());
        Place.textProperty().bindBidirectional(reservationVue.getPropPlace());
        recup_vol.textProperty().bindBidirectional(reservationVue.getPropVol());
        tableViewReservations.setItems(modelReservation.getObsListReservations());

        tableViewReservations.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

            }
        });

        tableViewReservations.setItems(modelReservation.getObsListReservations());

        columnNom.setCellValueFactory(t -> t.getValue().getPropNom());
        columnPrenom.setCellValueFactory(t -> t.getValue().getPropPrenom());
        columnPlace.setCellValueFactory(t -> t.getValue().getPropPlace());
        columnVol.setCellValueFactory(t -> t.getValue().getPropVol());

        rempliCombo();

        //positions des sieges de l'avion
        double posx[] = {287, 317, 347, 377, 408, 438, 468, 498, 529, 559, 594, 626, 654, 682,
            710, 739, 767, 795, 823, 851, 879, 907, 936, 964, 992, 1020, 1049, 1077};
        double posy[] = {248, 230, 212, 170, 152, 135};
        ArrayList<Point2D> seats = new ArrayList<>();
        for (double x : posx) {
            for (double y : posy) {
                seats.add(new Point2D(x, y));
            }
        }
        //on cree l'avion et insere les donnees
        //avion = new Avion("air3il/gui/javafx/place/a320.png", seats);
        avion = new Avion("air3il/gui/javafx/vueavion/a320.png", seats);

        // ajout des places disponibles (selectionables)
        ArrayList<DtoPlace> places = new ArrayList<>();
        places.add(new DtoPlace(0, "a1", 0));
        places.add(new DtoPlace(1, "b1", 0));
        places.add(new DtoPlace(2, "c1", 0));
        places.add(new DtoPlace(3, "d1", 0));
        places.add(new DtoPlace(4, "e1", 0));
        places.add(new DtoPlace(5, "f1", 0));
        places.add(new DtoPlace(6, "a2", 0));
        places.add(new DtoPlace(7, "b2", 0));
        places.add(new DtoPlace(8, "c2", 0));
        avion.getItems().setAll(places);

        //gestion des evenements de selection
        avion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DtoPlace>() {
            @Override
            public void changed(ObservableValue<? extends DtoPlace> observable, DtoPlace oldValue, DtoPlace newValue) {
                if (oldValue != null) {
                    System.out.println("deselection de : " + oldValue.getRef());
                    Place.setText("");
                }
                if (newValue != null) {
                    System.out.println("selection de : " + newValue.getRef());
                    Place.setText(newValue.getRef());
                }
            }
        });

        //gestion des evenements sur avion
        /*   avion.getSelectionModel().selectedItemProperty().addListener(
                (selmod, oldValue, newValue) -> {
                    if(oldValue != null){
                        //on gere la deselection
           
                    }else{
                        //on gere la selection
                      
                    }
            });*/
    }

}
