package air3il.emb.dao.test;

import air3il.commun.dao.IDaoReservation;
import air3il.commun.dto.DtoContact;
import air3il.commun.dto.DtoReservation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ARSENE
 */
public class DaoReservation implements IDaoReservation {

    private final ManagerDao managerDao;
    private final Map<Integer, DtoReservation> mapReservations;

    // Constructeur
    public DaoReservation(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapReservations = managerDao.getMapReservations();
    }

    @Override
    public void inserer(DtoReservation reservation) {
        reservation.setId(managerDao.getNextIdReservation());
        affecterIdContact(reservation);

        mapReservations.put(reservation.getId(), reservation);
    }

    @Override
    public void supprimer(int idReservation) {
        mapReservations.remove(idReservation);
    }

    @Override
    public DtoReservation retrouver(int idReservaton) {

        return mapReservations.get(idReservaton);

    }

    @Override
    public List<DtoReservation> listerTout() {
        return new ArrayList<>(mapReservations.values());
    }

    @Override
    public void modifier(DtoReservation reservation) {
        mapReservations.replace(reservation.getId(), reservation);
    }
    
    
    	// Méthodes auxiliaires
	
	private void affecterIdContact( DtoReservation reservation ) {
		for( DtoContact t : reservation.getContacts() ) {
			if ( t.getId() == 0 ) {
				t.setId( managerDao.getNextContact() );
			}
		}
	}


}
