package air3il.emb.service.standard;

import air3il.commun.dao.IDaoPassager;
import air3il.commun.dao.IManagerDao;
import air3il.commun.dto.DtoPassager;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServicePassager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicePassager implements IServicePassager {

    // Logger
    private static final Logger logger = Logger.getLogger(ServicePassager.class.getName());

    // Champs 
    private final ManagerService managerService;
    private final IManagerDao managerDao;
    private final IDaoPassager daoPassager;

    // Constructeur
    public ServicePassager(ManagerService managerService) {
        this.managerService = managerService;
        managerDao = managerService.getManagerDao();
        daoPassager = managerDao.getDao(IDaoPassager.class);
    }

    // Actions 
    @Override
    public DtoPassager inserer(DtoPassager passager) throws ExceptionAppli {
        try {

            managerService.verifierAutorisationUtilisateur();
            verifierValiditeDonnees(passager);

            managerDao.transactionBegin();
            try {
                daoPassager.inserer(passager);
                managerDao.transactionCommit();
                return passager;
            } catch (Exception e) {
                managerDao.transactionRollback();
                throw e;
            }

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoPassager modifier(DtoPassager passager) throws ExceptionAppli {
        try {

            managerService.verifierAutorisationUtilisateur();
            verifierValiditeDonnees(passager);

            managerDao.transactionBegin();
            try {
                daoPassager.modifier(passager);
                managerDao.transactionCommit();
                return passager;
            } catch (Exception e) {
                managerDao.transactionRollback();
                throw e;
            }

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public void supprimer(int idPassager) throws ExceptionAppli {
        try {

            managerService.verifierAutorisationUtilisateur();

            managerDao.transactionBegin();
            try {
                daoPassager.supprimer(idPassager);
                managerDao.transactionCommit();
            } catch (Exception e) {
                managerDao.transactionRollback();
                throw e;
            }

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoPassager retrouver(int idPassager) throws ExceptionAppli {
        try {

            managerService.verifierAutorisationUtilisateur();
            return daoPassager.retrouver(idPassager);

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public List<DtoPassager> listerTout() throws ExceptionAppli {
        try {

            managerService.verifierAutorisationUtilisateur();
            return daoPassager.listerTout();

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    // Méthodes auxiliaires
    private void verifierValiditeDonnees(DtoPassager passager) throws ExceptionAppli {

        StringBuilder message = new StringBuilder();

        if (passager.getNom() == null || passager.getNom().isEmpty()) {
            message.append("\nLe nom est absent.");
        } else if (passager.getNom().length() > 25) {
            message.append("\nLe nom est trop long.");
        }

        if (passager.getPrenom() == null || passager.getPrenom().isEmpty()) {
            message.append("\nLe prénom est absent.");
        } else if (passager.getPrenom().length() > 25) {
            message.append("\nLe prénom est trop long.");
        }

      
        if (message.length() > 0) {
            throw new ExceptionValidation(message.toString().substring(1));
        }
    }

}
