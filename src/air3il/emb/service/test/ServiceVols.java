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

public class ServiceVols implements IServiceVols {

    // Logger
    private static final Logger logger = Logger.getLogger(IServiceVols.class.getName());

    // Champs 
    // Champs 
    private ManagerService managerService;

    private Map<Integer, DtoVol> mapVols;
    private Map<Integer, DtoPays> mapPays;
    private Map<Integer, DtoVol> mapVol;

    // Constructeur
    public ServiceVols(ManagerService managerService) {
        this.managerService = managerService;
        mapPays = managerService.getMapPays();
    }

    @Override
    public void ajoutVols(DtoVol vols) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierVols(DtoVol vols) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void annulerVols(DtoVol vols) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        System.err.println(date1);
        String dateD = null;
        List<DtoVol> lstVol = new ArrayList<>();
        for (int i = 0; i < listVol.size(); i++) {
            DtoVille villeD = listVol.get(i).getville_dep();
            DtoVille villeA = listVol.get(i).getville_arr();
            dateD = simpleDateFormat.format(listVol.get(i).getDate_dep());
            System.err.println(dateD);
            
            if (date1.equals(dateD)) {
                System.err.println("ici 1");
            }
            if (date1.equals(dateD)&&villeD.equals(depart) && villeA.equals(arrive)) {
                System.err.println("ici");
                lstVol.add(listVol.get(i));
            }
        }
        return lstVol;
    }

}
