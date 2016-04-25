package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoReservation;
import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoPlace;

public interface IDaoReservation {

    public void inserer(DtoReservation reservation);

    public void modifier(DtoReservation reservation);

    public void supprimer(DtoVol vol, DtoPlace place);

    public DtoReservation retrouver(DtoVol vol, DtoPlace place);

    public List<DtoReservation> listerTout();
}
