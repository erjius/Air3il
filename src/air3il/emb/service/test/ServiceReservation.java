package air3il.emb.service.test;

import air3il.commun.dto.DtoContact;
import air3il.commun.dto.DtoReservation;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceReservation;
import java.util.ArrayList;
import java.util.Collections;
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
            if (mapReservations.isEmpty()) {
                reservation.setId(1);
            } else {
                reservation.setId(Collections.max(mapReservations.keySet()) + 1);
            }
             for( DtoContact contact : reservation.getContacts()) {
				if ( contact.getId() == 0 ) {
					contact.setId( managerService.getProchainIdContact());
				}
			}

            mapReservations.put(reservation.getId(), reservation);
            return reservation;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoReservation modifier(DtoReservation reservation) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationUtilisateur();
            verifierValiditeDonnees(reservation);
for( DtoContact contact : reservation.getContacts() ) {
				if ( contact.getId() == 0 ) {
					contact.setId( managerService.getProchainIdContact() );
				}
			}
            mapReservations.replace(reservation.getId(), reservation);
            return reservation;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public void supprimer(int idReservation) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationUtilisateur();
            mapReservations.remove(idReservation);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoReservation retrouver(int idReservation) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationUtilisateur();
            DtoReservation reservation = mapReservations.get(idReservation);
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
        for( DtoContact telephoe : reservation.getContacts() ) {
			if ( telephoe.getTel()== null || telephoe.getTel().isEmpty() ) {
				message.append( "\nLlibellé absent pour le téléphone : " + telephoe.getEmail());
			} else 	if ( telephoe.getTel().length() > 25 ) {
				message.append( "\nLe libellé du téléphone est trop lon : " + telephoe.getTel() );
			}
			
			if ( telephoe.getEmail() == null || telephoe.getEmail().isEmpty() ) {
				message.append( "\nNuméro absent pour le téléphone : " + telephoe.getTel() );
			} else 	if ( telephoe.getEmail().length() > 25 ) {
				message.append( "\nLe numéro du téléphone est trop lon : " + telephoe.getEmail() );
			}
		}

        if (message.length() > 0) {
            throw new ExceptionValidation(message.toString().substring(1));
        }
    }

}
