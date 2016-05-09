
package air3il.commun.service;

import air3il.commun.dto.DtoPassager;
import air3il.commun.exception.ExceptionAppli;
import java.util.List;

public interface IServicePassager {
	
	
	public DtoPassager inserer( DtoPassager dto ) throws ExceptionAppli;
	
	public DtoPassager	modifier( DtoPassager dto ) throws ExceptionAppli;
	
	public void	supprimer( int idPassager ) throws ExceptionAppli;
	
	public DtoPassager retrouver( int idPassager ) throws ExceptionAppli;
	
	public List<DtoPassager> listerTout() throws ExceptionAppli;
	

}
