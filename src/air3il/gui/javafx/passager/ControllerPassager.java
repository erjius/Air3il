package air3il.gui.javafx.passager;

import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsPassager;
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
public class ControllerPassager implements IControllerJavaFx {

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
    private Button Ajouter;
    @FXML
    private Button Supprimer;
    @FXML
    private TableView<ObsPassager> tableViewPassagers;
    @FXML
    private TableColumn<ObsPassager, String> columnNom;
    @FXML
    private TableColumn<ObsPassager, String> columnPrenom;
    @FXML
    private TableColumn<ObsPassager, String> columnTel;
    @FXML
    private TableColumn<ObsPassager, String> columnEmail;
    
    
	// Champs
	private ManagerGui	managerGui;
	private ModelPassager	modelPassager;
	
	
	// Actions
	
	@FXML
	private void doActualiser() {
		try {
			modelPassager.actualiserListe();
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		}
	}
	
	@FXML
	private void doAjouter() throws Exception {
     
               modelPassager.ValiderMiseAJour();             
		modelPassager.preparerAjouter();
		managerGui.showView( EnumView.Passager);
 
	}
	
	@FXML
	private void doModifier() {
		ObsPassager p = tableViewPassagers.getSelectionModel().getSelectedItem();
		modelPassager.preparerModifier(p);
		managerGui.showView( EnumView.Passager );
	}
	
	@FXML
	private void doSupprimer() {
		
		if ( managerGui.demanderConfirmation("Etes-vous sûr de voulir supprimer cette passager ?" ) ) {
			ObsPassager p = tableViewPassagers.getSelectionModel().getSelectedItem();
			try {
				modelPassager.supprimer(p);
			} catch (Exception e) {
				managerGui.afficherErreur(e);
			}
		}
	}

        
        

    // Initialisations
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {

        // Injection de dépendances
        this.managerGui = managerGui;
        modelPassager = managerGui.getModel(ModelPassager.class);

        // Data binding
        ObsPassager passagerVue = modelPassager.getPassagerVue();
        tableViewPassagers.setItems(modelPassager.getObsListPassagers());

        Nom.textProperty().bindBidirectional(passagerVue.getPropNom());
        Prenom.textProperty().bindBidirectional(passagerVue.getPropPrenom());
        Tel.textProperty().bindBidirectional(passagerVue.getPropTel());
        Email.textProperty().bindBidirectional(passagerVue.getPropEmail());
        
         columnNom.setCellValueFactory(t -> t.getValue().getPropNom());
        columnPrenom.setCellValueFactory(t -> t.getValue().getPropPrenom());
        columnTel.setCellValueFactory(t -> t.getValue().getPropTel());
        columnEmail.setCellValueFactory(t -> t.getValue().getPropEmail());

        Ajouter.setDisable(false);
        Supprimer.setDisable(true);

        tableViewPassagers.getSelectionModel().getSelectedItems().addListener(
                (ListChangeListener<ObsPassager>) (c) -> {
                    if (tableViewPassagers.getSelectionModel().getSelectedItem() == null) {
                        Ajouter.setDisable(false);
                        Supprimer.setDisable(true);
                    } else {
                        // Ajouter.setDisable(true);
                        Supprimer.setDisable(false);

                    }
                });
    }

}
