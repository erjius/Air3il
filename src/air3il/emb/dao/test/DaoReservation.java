package air3il.emb.dao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import air3il.commun.dao.IDaoReservation;
import air3il.commun.dto.DtoPlace;
import air3il.commun.dto.DtoReservation;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import java.util.Map;

public class DaoReservation implements IDaoReservation {
    
    // Champs
    private final ManagerDao managerDao;
    private final Map<String, DtoReservation> mapReservations;
    private final Map<Integer, DtoVille> mapVilles;

    // Constructeur
    public DaoReservation(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapReservations = managerDao.getMapReservations();
        mapVilles = managerDao.getMapVilles();
    }

    // Actions
    @Override
    public void inserer(DtoReservation reservation) {
        mapReservations.put((reservation.getVol().getId() + "-" + reservation.getPlace().getId()), reservation);
    }

    @Override
    public void modifier(DtoReservation reservation) {
        mapReservations.replace((reservation.getVol().getId() + "-" + reservation.getPlace().getId()), reservation);
    }

    @Override
    public void supprimer(DtoVol vol, DtoPlace place) {
        mapReservations.remove(vol.getId() + "-" + place.getId());
    }

    @Override
    public DtoReservation retrouver(DtoVol vol, DtoPlace place) {
        return mapReservations.get(vol.getId() + "-" + place.getId());
    }

    @Override
    public List<DtoReservation> listerTout() {
        return new ArrayList<>(mapReservations.values());
    }
   
}
