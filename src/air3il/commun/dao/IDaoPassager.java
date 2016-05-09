package air3il.commun.dao;

import air3il.commun.dto.DtoPassager;
import java.util.List;


public interface IDaoPassager {

	public void inserer( DtoPassager personne );

	public void modifier( DtoPassager personne );

	public void supprimer( int idPassager );

	public DtoPassager retrouver( int idPassager );

	public List<DtoPassager> listerTout();

}
