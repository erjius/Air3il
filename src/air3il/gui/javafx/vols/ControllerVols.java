package air3il.gui.javafx.vols;

import air3il.gui.javafx.systeme.*;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsCompte;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class ControllerVols implements IControllerJavaFx {

    // Champs
    private ManagerGui managerGui;
    private ModelVols modelVols;
    private ModelSysteme modelSysteme;
    
    //Composant de la vue
    @FXML
    private TextField paysAller;
    @FXML
    private TextField villeAller;
    @FXML
    private TextField paysRetour;
    @FXML
    private TextField villeRetour;
    @FXML
    private RadioButton allerRetour;
    @FXML
    private RadioButton allerSimple;
    @FXML
    private DatePicker dateRetour;
    @FXML
    private DatePicker dateAller;
    @FXML
    private ComboBox nbPassager;
    @FXML
    private Tab ongletRetour;
    @FXML
    private Tab ongletAller;
    @FXML
    private Button btReserver;
    @FXML
    private Button btRechercher;
    
    
    
    public void doAfficherDateRetour(){
        dateRetour.setVisible(true);
        ongletRetour.setDisable(false);
    }
    public void doMasquerDateretour(){
        dateRetour.setVisible(false);
        ongletRetour.setDisable(true);
    }
    public ObservableList doRemplirComboBox(){ 
    ObservableList<String> passagerBox;
        passagerBox = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8"
        );
       
        
        return passagerBox;
    }
    


    // Initialisation du Controller
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {

        // Injection  de dépendances
        this.managerGui = managerGui;
        modelVols = managerGui.getModel(ModelVols.class);
        //combo box 
        nbPassager.setValue("1");
        nbPassager.setItems(doRemplirComboBox());
        //recuperer les données de text 
        paysAller.textProperty().bindBidirectional(modelVols.getProdPaysAller());
        villeAller.textProperty().bindBidirectional(modelVols.getProdVilleAller());
        paysRetour.textProperty().bindBidirectional(modelVols.getProdPaysRetour());
        villeRetour.textProperty().bindBidirectional(modelVols.getProdVillRetour());
        //recuperer les dates 
        modelVols.setDateAller(dateAller.getValue());
        modelVols.setDateRetour(dateRetour.getValue());
        //recuprer les nbPassagers;
        modelVols.setNbPassager((int) nbPassager.getValue());
        
    }

}
