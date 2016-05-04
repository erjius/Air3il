package air3il.emb.service.test;

import java.util.List;
import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.service.IServiceVols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ServiceVols implements IServiceVols {

    // Logger
    private static final Logger logger = Logger.getLogger(IServiceVols.class.getName());

    // Champs 
    // Champs 
    private ManagerService managerService;

    private Map<Integer, DtoVol> mapVols;
    private Map<Integer, DtoPays> mapPays;
    private Map<Integer, DtoVol> mapVol;

    // a modifier des que le service pays et ville
    // Constructeur
    public ServiceVols(ManagerService managerService) {
        this.managerService = managerService;
        mapPays = managerService.getMapPays();
    }

    @Override
    public void ajoutVols(DtoVol vols) throws ExceptionAppli {
        managerService.getListVols().add(vols);
                
    }

    @Override
    public void modifierVols(DtoVol vols) throws ExceptionAppli {
        
       managerService.getListVols().remove(vols.getId());
       managerService.getListVols().add(vols);
    }

    @Override
    public void annulerVols(DtoVol vols) throws ExceptionAppli {
        managerService.getListVols().get(vols.getId()).setEtat("Annuler");
    }

    @Override
    public List<DtoVol> lstToutVol() throws ExceptionAppli {
        List<DtoVol> lstVol = managerService.getListVols();

        return lstVol;

    }

    @Override
    public List<DtoVol> listervols(DtoVille depart, DtoVille arrive, Date date) throws ExceptionAppli {

        List<DtoVol> listVol = managerService.getListVols();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date1 = simpleDateFormat.format(date);

        String dateD = null;
        List<DtoVol> lstVol = new ArrayList<>();
        for (int i = 0; i < listVol.size(); i++) {
            DtoVille villeD = listVol.get(i).getville_dep();
            DtoVille villeA = listVol.get(i).getville_arr();
            dateD = simpleDateFormat.format(listVol.get(i).getDate_dep());

            if (date1.equals(dateD) && villeD.equals(depart) && villeA.equals(arrive)) {
                lstVol.add(listVol.get(i));
            }
        }
        if (lstVol.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de recherche ");
            alert.setContentText("Il n'y a pas de vol correspondant a votre recherche.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

        return lstVol;
    }

    @Override
    public List<DtoVille> lstToutVille() throws ExceptionAppli {
        return managerService.getListVille();
    }

}
