/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.gui.javafx.pays;

import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVille;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IManagerService;
import air3il.commun.service.IServicePays;
import air3il.gui.javafx.ManagerGui;
import com.sun.javafx.collections.ObservableListWrapper;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableListValue;
import javafx.collections.ObservableList;

/**
 *
 * @author projet
 */
public class ModelPaysVille {

    private DtoPays pays;
    private DtoVille ville;
    
    private ObservableList<DtoPays> obslistePays;
   
    private IServicePays servicePays;
    private ManagerGui managerGui;

    public ModelPaysVille(ManagerGui m) {
        managerGui = m ;
        try {
            servicePays = m.getManagerService().getService(IServicePays.class);
        } catch (ExceptionAppli ex) {
            Logger.getLogger(ModelPaysVille.class.getName()).log(Level.SEVERE, "erreur de chargement de ServicePays", ex);
        }
    }

    public String getPays() {
        return pays.getNom();
    }

    public void setNewPays(String pays) {
        this.pays = new DtoPays(0, pays);
    }
    
    public void setPays(DtoPays pays){
        this.pays =pays; 
    }
    
    public DtoVille getVille() {
        return ville;
    }

    public void setNewVille(String ville) {
        this.ville = new DtoVille(0, ville, null);
    }
    
    public ObservableList<DtoPays> chargerListePays(){
        try {
            obslistePays = new ObservableListWrapper<>(servicePays.listerToutPays());
        } catch (ExceptionAppli ex) {
            Logger.getLogger(ModelPaysVille.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obslistePays;
    }

    public void envoyer() throws ExceptionAppli ,ExceptionValidation{
        if(pays.getId()==0){
            obslistePays.add(servicePays.insererPays(pays));
        }
        
        servicePays.insererVille(ville);
    }
}