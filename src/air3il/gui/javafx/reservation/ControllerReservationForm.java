
package air3il.gui.javafx.reservation;

import air3il.gui.javafx.IControllerJavaFx;
import air3il.gui.javafx.ManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author anega
 */
public class ControllerReservationForm implements IControllerJavaFx{
    
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
    private ManagerGui managerGui;
       
       @FXML
       public void doAjouter(){
        Nom.getText().toString();
        Prenom.getText().toString();
        Tel.getText().toString();
        Email.getText().toString();
          
       }
       public void setManagerGui(ManagerGui managerGui) throws Exception {
        // Injection  de dépendances
        this.managerGui = managerGui;
       
       
    }
}
