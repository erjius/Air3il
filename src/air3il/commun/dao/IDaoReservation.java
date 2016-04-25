package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoReservation;
import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoPlace;

public interface IDaoReservation {

    
    /**
     * Pour ajouter une reservation
     * @param reservation 
     */
    public void inserer(DtoReservation reservation);

    
    /**
     * Pour modifier une reservation
     * @param reservation 
     */
    public void modifier(DtoReservation reservation);

    
    /**
     * Pour supprimer une reservation
     * @param vol
     * @param place 
     */
    public void supprimer(DtoVol vol, DtoPlace place);
    
    
    /**
     * Pour trouver une reservation à parir de son id
     * @param vol
     * @param place
     * @return 
     */
    public DtoReservation retrouver(DtoVol vol, DtoPlace place);

    
    /**
     * Pour obtenir la liste de toutes les reservations de la base de donnée
     * @return 
     */
    public List<DtoReservation> listerTout();
}
