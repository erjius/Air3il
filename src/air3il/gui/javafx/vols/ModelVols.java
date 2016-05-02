package air3il.gui.javafx.vols;

import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import air3il.commun.exception.ExceptionAppli;
import air3il.gui.javafx.ManagerGui;
import air3il.commun.service.IManagerService;
import air3il.commun.service.IServicePays;
import air3il.commun.service.IServiceVols;
import com.sun.javafx.collections.ObservableListWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;

public final class ModelVols {

    // Donnée abservable 
    private DtoPays PaysAller = null;
    private DtoVille VilleAller = null;
    private DtoPays PaysRetour = null;
    private DtoVille VilleRetour = null;
    private Date dateAller = null;
    private Date dateRetour = null;
    private int NbPassager;
    private final ArrayList<DtoPays> listePays = new ArrayList<>();
    private ObservableList<DtoVol> lstVol;

    public DtoVille getVilleAller() {
        return VilleAller;
    }

    public DtoVille getVilleRetour() {
        return VilleRetour;
    }

    public Date getDateAller() {
        return dateAller;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public int getNbPassager() {
        return NbPassager;
    }

    public void setVilleAller(DtoVille VilleAller) {
        this.VilleAller = VilleAller;
    }

    public void setPaysRetour(DtoPays PaysRetour) {
        this.PaysRetour = PaysRetour;
    }

    public void setVilleRetour(DtoVille VilleRetour) {
        this.VilleRetour = VilleRetour;
    }

    public ObservableList<DtoVol> getListVol() {
        return lstVol;
    }

    public void setPaysAller(DtoPays PaysAller) {
        this.PaysAller = PaysAller;
    }

    public ArrayList<DtoPays> getListePays() {
        return listePays;
    }

    public void setDateAller(Date dateAller) {
        this.dateAller = dateAller;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public void setNbPassager(int nbPassager) {
        this.NbPassager = nbPassager;
    }

    public List<DtoVille> lstVilleRechercherAller() throws ExceptionAppli {

        List<DtoVille> lstVille = serviceVols.lstToutVille();
        
        
        ArrayList<DtoVille> lstVille1 = new ArrayList<>();
        for (int i = 0; i < lstVille.size(); i++) {
            if (lstVille.get(i).getPays() == PaysAller) {
                lstVille1.add(lstVille.get(i));
            }
        }
        return lstVille1;
    }

    public List<DtoVille> lstVilleRechercherRetour() throws ExceptionAppli {

        List<DtoVille> lstVille = serviceVols.lstToutVille();    
        
        ArrayList<DtoVille> lstVille1 = new ArrayList<>();
        for (int i = 0; i < lstVille.size(); i++) {
            if (lstVille.get(i).getPays() == PaysRetour) {
                lstVille1.add(lstVille.get(i));
            }
        }
        return lstVille1;
    }
    public void listerLesVols() throws ExceptionAppli{
      
        lstVol=new ObservableListWrapper<>(serviceVols.listervols(VilleAller, VilleRetour, dateAller));
        
    }
    private final IServiceVols serviceVols;
    private final IServicePays servicePays;
    private final ManagerGui managerGui;
    private final IManagerService managerService;
    // Constructeur

    public ModelVols(ManagerGui managerGui) throws Exception {
        this.managerGui = managerGui;
        this.managerService = managerGui.getManagerService();
        serviceVols = managerService.getService(IServiceVols.class);
        servicePays = managerService.getService(IServicePays.class);

        listePays.addAll(servicePays.listerTout());
        lstVol=null;
//        lstVol = new ObservableListWrapper<>(serviceVols.lstToutVol());

    }
}
