package air3il.gui.javafx.vols;

import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import air3il.commun.exception.ExceptionAppli;
import air3il.gui.javafx.systeme.*;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import com.sun.javafx.collections.ObservableListWrapper;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class ControllerVols implements IControllerJavaFx {

    // Champs
    private ManagerGui managerGui;
    private ModelVols modelVols;
    private ModelSysteme modelSysteme;

    //Composant de la vue
    @FXML
    private ComboBox<DtoPays> paysAller;
    @FXML
    private ComboBox<DtoVille> villeAller;
    @FXML
    private ComboBox<DtoPays> paysRetour;
    @FXML
    private ComboBox<DtoVille> villeRetour;
    @FXML
    private RadioButton allerRetour;
    @FXML
    private RadioButton allerSimple;
    @FXML
    private DatePicker dateRetour;
    @FXML
    private DatePicker dateAller;
    @FXML
    private ComboBox<Integer> nbPassager;
    @FXML
    private TabPane onglets;
    @FXML
    private Tab ongletRetour;
    @FXML
    private Tab ongletAller;
    @FXML
    private Button btReserver;
    @FXML
    private Button btRechercher;
    @FXML
    private ListView<DtoVol> listviews;
    @FXML
    private ListView<DtoVol> listViewsR;

    public void doAfficherDateRetour() {
        dateRetour.setVisible(true);
        ongletRetour.setDisable(false);
    }

    public void doMasquerDateretour() {
        dateRetour.setVisible(false);
        ongletRetour.setDisable(true);
    }
    public void doSubmit(){
        modelVols.submit();
        modelVols.cleanAll();
        nbPassager.setItems(doRemplirComboBox());
        dateAller.setValue(LocalDate.now());
        dateRetour.setValue(LocalDate.now());
        allerSimple.getToggleGroup().selectToggle(allerSimple);
        listviews.setItems(null);
        listviews.setItems(null);
        onglets.selectionModelProperty().get().select(ongletAller);
        nbPassager.setValue(1);
        doMasquerDateretour();
        paysAller.setValue(null);
        paysRetour.setValue(null);
        
    }
    public void doRechercheVol() throws ExceptionAppli {
        modelVols.setVilleAller(villeAller.getValue());
        modelVols.setVilleRetour(villeRetour.getValue());
        modelVols.setNbPassager(nbPassager.getValue());
        //recuperer les dates 
        modelVols.setDateAller(Date.valueOf(dateAller.getValue()));
        modelVols.setDateRetour(Date.valueOf(dateRetour.getValue()));

        modelVols.listerLesVols();
        listviews.setItems(modelVols.getListVol());
        if (allerRetour.isSelected()) {
            modelVols.listerLesVolsRetours();
            listViewsR.setItems(modelVols.getLstVolR());
        }
    }

    @FXML
    private void listViewOnMouseClicked(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            if (allerRetour.isSelected()) {
                if (modelVols.getVolAller() != null) {
                    onglets.selectionModelProperty().get().select(ongletRetour);
                }
            }
        }
    }

    public ObservableList<Integer> doRemplirComboBox() {
        ObservableList<Integer> passagerBox;
        passagerBox = FXCollections.observableArrayList(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8
        );

        return passagerBox;
    }

    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {

        // Injection  de dépendances
        this.managerGui = managerGui;
        modelVols = managerGui.getModel(ModelVols.class);
        
        
        doSubmit();
//recuperer les données de text 
        paysAller.setItems(new ObservableListWrapper<>(modelVols.getListePays()));
        paysAller.valueProperty().addListener(new ChangeListener<DtoPays>() {
            @Override
            public void changed(ObservableValue<? extends DtoPays> observable, DtoPays oldValue, DtoPays newValue) {
                modelVols.setPaysAller(newValue);
                villeAller.setValue(null);
                try {
                    villeAller.setItems(new ObservableListWrapper<>(modelVols.lstVilleRechercherAller()));
                } catch (ExceptionAppli ex) {
                    Logger.getLogger(ControllerVols.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        paysRetour.setItems(new ObservableListWrapper<>(modelVols.getListePays()));
        paysRetour.valueProperty().addListener(new ChangeListener<DtoPays>() {
            @Override
            public void changed(ObservableValue<? extends DtoPays> observable, DtoPays oldValue, DtoPays newValue) {
                modelVols.setPaysRetour(newValue);
                villeRetour.setValue(null);
                try {
                    villeRetour.setItems(new ObservableListWrapper<>(modelVols.lstVilleRechercherRetour()));
                } catch (ExceptionAppli ex) {
                    Logger.getLogger(ControllerVols.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        listviews.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<DtoVol>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends DtoVol> c) {
                c.next();
                if (c.wasAdded()) {
                    modelVols.setVolAller(c.getAddedSubList().get(0));
                    if (allerSimple.isSelected() || modelVols.getVolRetour() != null) {
                        btReserver.setDisable(false);
                    }
                } else if (c.wasRemoved()) {
                    btReserver.setDisable(true);
                    modelVols.setVolAller(null);
                }
            }
        });
        listViewsR.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<DtoVol>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends DtoVol> c) {
                c.next();
                if (c.wasAdded()) {
                    modelVols.setVolRetour(c.getAddedSubList().get(0));
                    if (modelVols.getVolAller() != null) {
                        btReserver.setDisable(false);
                    }
                } else if (c.wasRemoved()) {
                    modelVols.setVolAller(null);
                    btReserver.setDisable(true);
                }
            }
        });
    }
}
