package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoReservation;

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
     * @param idReservation
     */
    public void supprimer(int idReservation);
    
    
    /**
     * Pour trouver une reservation à parir de son id
     * @param idReservation
     * @return 
     */
    public DtoReservation retrouver(int  idReservation);

    
    /**
     * Pour obtenir la liste de toutes les reservations de la base de donnée
     * @return 
     */
    public List<DtoReservation> listerTout();
}
