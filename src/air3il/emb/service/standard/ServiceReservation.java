package air3il.emb.service.standard;

import air3il.commun.dao.IDaoReservation;
import air3il.commun.dao.IManagerDao;
import air3il.commun.dto.DtoReservation;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceReservation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ARSENE
 */
public class ServiceReservation implements IServiceReservation {

    // Logger
    private static final Logger logger = Logger.getLogger(ServiceReservation.class.getName());

    // Champs 
    private final ManagerService managerService;
    private final IManagerDao managerDao;
    private final IDaoReservation daoReservation;

    // Constructeur
    public ServiceReservation(ManagerService managerService) {
        this.managerService = managerService;
        managerDao = managerService.getManagerDao();
        daoReservation = managerDao.getDao(IDaoReservation.class);
    }

    /**
     *
     * @param reservation
     * @return
     * @throws ExceptionAppli
     */
    @Override
    public DtoReservation inserer(DtoReservation reservation) throws ExceptionAppli {
        try {

            managerService.verifierAutorisationUtilisateur();
            verifierValiditeDonnees(reservation);

            managerDao.transactionBegin();
            try {
                daoReservation.inserer(reservation);
                managerDao.transactionCommit();
                return reservation;
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
    public List<DtoReservation> listerTout() throws ExceptionAppli {

        try {

            managerService.verifierAutorisationUtilisateur();
            return daoReservation.listerTout();

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    // Méthodes auxiliaires
    private void verifierValiditeDonnees(DtoReservation reservation) throws ExceptionAppli {

        StringBuilder message = new StringBuilder();

        if (reservation.getNom_pass() == null || reservation.getNom_pass().isEmpty()) {
            message.append("\nLe nom est absent.");
        } else if (reservation.getNom_pass().length() > 25) {
            message.append("\nLe nom est trop long.");
        }

        if (reservation.getPrenom_pass() == null || reservation.getPrenom_pass().isEmpty()) {
            message.append("\nLe prénom est absent.");
        } else if (reservation.getPrenom_pass().length() > 25) {
            message.append("\nLe prénom est trop long.");
        }

        if (message.length() > 0) {
            throw new ExceptionValidation(message.toString().substring(1));
        }
    }

}
