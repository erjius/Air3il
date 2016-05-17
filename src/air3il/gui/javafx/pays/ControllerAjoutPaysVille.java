package air3il.gui.javafx.pays;

import air3il.commun.dto.DtoPays;
import air3il.commun.exception.ExceptionAppli;
import air3il.gui.javafx.EnumView;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.systeme.ControllerPrincipal;
import air3il.gui.javafx.systeme.ModelSysteme;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

public class ControllerAjoutPaysVille implements IControllerJavaFx {

    // Champs
    private ManagerGui managerGui;

    private boolean modeajout = false;

    @FXML
    TextField nom_de_laville;

    @FXML
    TextField nom_nouveau_pays;

    @FXML
    Label label_nouveau_pays;

    @FXML
    ListView<DtoPays> liste_pays;

    @FXML
    StackPane satckp;

    ModelPaysVille modelPaysVille;
  

    // Initialisation du Controller
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {
        this.managerGui = managerGui;
        
        liste_pays.managedProperty().bind(liste_pays.visibleProperty());
        label_nouveau_pays.managedProperty().bind(label_nouveau_pays.visibleProperty().not());
        nom_nouveau_pays.managedProperty().bind(nom_nouveau_pays.visibleProperty().not());
        modelPaysVille = managerGui.getModel(ModelPaysVille.class);
        nom_de_laville.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        modelPaysVille.setNewVille(newValue);
        });
        liste_pays.setItems(modelPaysVille.chargerListePays());
        liste_pays.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends DtoPays> c) -> {
            c.next();
            if (c.wasAdded()) {
                modelPaysVille.setPays(c.getList().get(0));
            } else {
                modelPaysVille.setPays(null);
            }
        });
        nom_nouveau_pays.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            modelPaysVille.setNewPays(newValue);
        });
    }

    public void soumettre() {
        try {
             
            modelPaysVille.envoyer();


        } catch (ExceptionAppli ex) {
            
            Logger.getLogger(ControllerAjoutPaysVille.class.getName()).log(Level.SEVERE, null, ex);
            basculer_mode();
        }

    }
    

    public void basculer_mode() {
        modeajout = !modeajout;
        liste_pays.setVisible(!modeajout);
        label_nouveau_pays.setVisible(modeajout);
        nom_nouveau_pays.setVisible(modeajout);
        Node n = satckp.getChildren().remove(0);
 
        satckp.getChildren().add(n);
        if (modeajout) {
            modelPaysVille.setNewPays(nom_nouveau_pays.getText());
        } else {
            liste_pays.getSelectionModel().getSelectedItems().forEach((selection) -> {
                modelPaysVille.setPays(selection);
            });
        }
    }

    public void initChamp() {
        label_nouveau_pays.setText("");
        nom_de_laville.setText("");
    }

}
