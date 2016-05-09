package air3il.emb.service.test;

import air3il.commun.dto.DtoReservation;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceReservation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private final Map<Integer, DtoReservation> mapReservations;
    private  List<DtoReservation> listReservationAller = new ArrayList<>();

    public void setListReservationAller(List<DtoReservation> listReservationAller) {
        this.listReservationAller = listReservationAller;
    }

    public void setListReservationRetour(List<DtoReservation> listReservationRetour) {
        this.listReservationRetour = listReservationRetour;
    }
    private  List<DtoReservation> listReservationRetour= new ArrayList<>();

    public List<DtoReservation> getListReservationAller() {
        return listReservationAller;
    }

    public List<DtoReservation> getListReservationRetour() {
        return listReservationRetour;
    }
    
    // Constructeur
    public ServiceReservation(ManagerService managerService) {
        this.managerService = managerService;
        mapReservations = managerService.getMapReservations();
    }

    @Override
    public DtoReservation inserer(DtoReservation reservation) throws ExceptionAppli {

        try {
            managerService.verifierAutorisationUtilisateur();
            verifierValiditeDonnees(reservation);

            return reservation;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }

    }

    @Override
    public List<DtoReservation> listerTout() throws ExceptionAppli {

        try {
            managerService.verifierAutorisationUtilisateur();
            return new ArrayList<>(mapReservations.values());
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }

    }

    // Méthodes auxiliaires
    private void verifierValiditeDonnees(DtoReservation reservation) throws ExceptionAppli {

        StringBuilder message = new StringBuilder();
        try {
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
        } catch (Exception e) {
        }
        
    }

}
