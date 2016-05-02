package air3il.emb.service.standard;

import air3il.commun.dao.IDaoPays;
import air3il.commun.dao.IDaoVille;
import air3il.commun.dao.IDaoVol;
import air3il.commun.dao.IManagerDao;
import java.util.List;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.service.IServiceVols;
import java.util.Date;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Logger;


public class ServiceVols implements IServiceVols {
   
    // Logger
    private static final Logger logger = Logger.getLogger(IServiceVols.class.getName());

    // Champs 
    private IDaoPays iDaoPays;
    private IDaoVille iDeoville;
    private IDaoVol idaoVol;
    private IManagerDao managerDao;
    

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DtoVol> listervols(DtoVille depart, DtoVille arrive, Date date) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   


}
