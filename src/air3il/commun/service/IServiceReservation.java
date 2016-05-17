
package air3il.commun.service;

import air3il.commun.dto.DtoReservation;
import air3il.commun.exception.ExceptionAppli;
import java.util.List;

/**
 *
 * @author ARSENE
 */
public interface IServiceReservation {
    
    
    	
	public DtoReservation inserer( DtoReservation dto ) throws ExceptionAppli;
	
	public DtoReservation	modifier( DtoReservation dto ) throws ExceptionAppli;
	
	public void	supprimer( int idReservation ) throws ExceptionAppli;
	
	public DtoReservation retrouver( int idReservation ) throws ExceptionAppli;
	
	public List<DtoReservation> listerTout() throws ExceptionAppli;
	
}
