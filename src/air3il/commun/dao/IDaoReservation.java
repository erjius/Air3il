package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoReservation;
import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoPlace;
import air3il.commun.dto.DtoClient;

public interface IDaoReservation {

    public DtoReservation inserer(DtoReservation reservation);

    public DtoReservation modifier(DtoReservation reservation);

    public void supprimer(DtoVol vol, DtoPlace place);

    public DtoReservation retrouver(DtoVol vol, DtoPlace place);

    public List<DtoReservation> listerTout();
}
