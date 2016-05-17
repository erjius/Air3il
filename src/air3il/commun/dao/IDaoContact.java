package air3il.commun.dao;

import air3il.commun.dto.DtoContact;
import air3il.commun.dto.DtoReservation;
import java.util.List;



public interface IDaoContact {

	public void inserer( DtoContact contact );

	public void modifier( DtoContact contact );

	public void supprimer( int idContact );

	public List<DtoContact> listerPourReservation( DtoReservation reservation );

}
