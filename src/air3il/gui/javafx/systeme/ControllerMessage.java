package air3il.gui.javafx.systeme;

import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerMessage implements IControllerJavaFx {

    // Composants de la vue
    @FXML
    private Label labelTitre;
    @FXML
    private Label labelMessage;

    // Injecteurs
    @Override
    public void setManagerGui(ManagerGui managerGui) throws Exception {

        // Injection  de dépendances
        ModelSysteme modelSysteme = managerGui.getModel(ModelSysteme.class);

        // Data binding
        labelTitre.textProperty().bind(modelSysteme.getPropTitre());
        labelMessage.textProperty().bind(modelSysteme.getPropMessage());
    }

}
