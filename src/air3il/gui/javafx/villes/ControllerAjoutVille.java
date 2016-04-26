package air3il.gui.javafx.villes;

import air3il.commun.dto.DtoPays;
import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import java.awt.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ControllerAjoutVille implements IControllerJavaFx {

    // Champs
    private ManagerGui managerGui;
    @FXML
    Button Nouveau_Pays;
    @FXML
    TextField Nom_de_laville;
    @FXML
    ListView<DtoPays> liste_pays;
    @FXML
    Button Valider;

    // Initialisation du Controller
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {
        this.managerGui = managerGui;
    } 
}
