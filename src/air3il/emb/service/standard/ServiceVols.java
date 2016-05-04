package air3il.emb.service.standard;

import java.util.List;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.service.IServiceVols;
import air3il.emb.dao.jdbc.ManagerDao;
import air3il.emb.dao.test.DaoVol;
import air3il.gui.javafx.systeme.ModelSysteme;
import air3il.gui.javafx.vols.ModelVols;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceVols implements IServiceVols{
   
    // Logger
    private static final Logger logger = Logger.getLogger(IServiceVols.class.getName());


    // Champs 
    private ModelSysteme model;
    private ManagerDao managerDao;
    private DaoVol daoVol;
    // Injecteurs
    
    public void setModel(ModelSysteme model) {
        this.model = model;
    }

    
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
        this.daoVol = managerDao.getDao(DaoVol.class);
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
        try {
            return daoVol.listerTout();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public List<DtoVol> listervols(DtoVille depart, DtoVille arrive, Date date) throws ExceptionAppli {
        try {
           return daoVol.recherher(arrive, depart, date);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }


    

   


}
