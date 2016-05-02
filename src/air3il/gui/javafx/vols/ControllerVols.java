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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;

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
    private Tab ongletRetour;
    @FXML
    private Tab ongletAller;
    @FXML
    private Button btReserver;
    @FXML
    private Button btRechercher;
    @FXML
    private ListView<DtoVol> listviews;

    public void doAfficherDateRetour() {
        dateRetour.setVisible(true);
        ongletRetour.setDisable(false);
    }

    public void doMasquerDateretour() {
        dateRetour.setVisible(false);
        ongletRetour.setDisable(true);
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
        //combo box 
        nbPassager.setValue(1);
        nbPassager.setItems(doRemplirComboBox());

        dateAller.setValue(LocalDate.now());
        dateRetour.setValue(LocalDate.now());
        //recuperer les données de text 
        paysAller.setItems(new ObservableListWrapper<>(modelVols.getListePays()));
        paysAller.valueProperty().addListener(new ChangeListener<DtoPays>() {
            @Override
            public void changed(ObservableValue<? extends DtoPays> observable, DtoPays oldValue, DtoPays newValue) {
                modelVols.setPaysAller(newValue);
                villeAller.setValue(null);
                villeAller.setItems(new ObservableListWrapper<>(modelVols.lstVilleRechercherAller()));
            }
        });

        paysRetour.setItems(new ObservableListWrapper<>(modelVols.getListePays()));
        paysRetour.valueProperty().addListener(new ChangeListener<DtoPays>() {
            @Override
            public void changed(ObservableValue<? extends DtoPays> observable, DtoPays oldValue, DtoPays newValue) {
                modelVols.setPaysRetour(newValue);
                villeRetour.setValue(null);
                villeRetour.setItems(new ObservableListWrapper<>(modelVols.lstVilleRechercherRetour()));
            }
        });
    }
}
