package air3il.gui.javafx.vols;

import air3il.gui.javafx.systeme.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dto.DtoCompte;
import air3il.commun.dto.DtoGroupe;
import air3il.commun.exception.ExceptionAutorisation;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.groupe.ModelGroupe;
import air3il.gui.javafx.obs.ObsCompte;
import air3il.commun.service.IManagerService;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.util.resources.LocaleData;

public class ModelVols {
    
   // Donnée abservable 
    private final StringProperty prodPaysAller = new SimpleStringProperty();
    private final StringProperty prodVilleAller = new SimpleStringProperty();
    private final StringProperty prodPaysRetour = new SimpleStringProperty();
    private final StringProperty prodVillRetour = new SimpleStringProperty();
    private String dateAller;
    private String dateRetour;
    private int nbPassager;

    public void setDateAller(LocalDate dateAller) {
        //convertion en string 
        String date= dateAller.toString(); 
        this.dateAller = date;
    }

    public void setDateRetour(LocalDate dateRetour) {
        //conversion en string
        String date= dateRetour.toString();
        this.dateRetour = date;
    }

    public void setNbPassager(int nbPassager) {
        this.nbPassager = nbPassager;
    }
    
    public ObservableList<DtoCompte> getObsListVol() {
        return obsListVol;
    }
    private final ObservableList<DtoCompte> obsListVol = FXCollections.observableArrayList();

    public StringProperty getProdPaysAller() {
        return prodPaysAller;
    }

    public StringProperty getProdVilleAller() {
        return prodVilleAller;
    }

    public StringProperty getProdPaysRetour() {
        return prodPaysRetour;
    }

    public StringProperty getProdVillRetour() {
        return prodVillRetour;
    }
    public ObservableList<DtoCompte> getObsListVols() {
        return obsListVol;
    }
   
    
    private final ManagerGui managerGui;
    private final IManagerService managerService;
     // Constructeur
    public ModelVols(ManagerGui managerGui) throws Exception {
        this.managerGui = managerGui;
        this.managerService = managerGui.getManagerService();
    }

    public void rechercherVolsSimple(){
        
    }
}
