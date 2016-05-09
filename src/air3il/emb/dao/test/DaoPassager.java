package air3il.emb.dao.test;

import air3il.commun.dao.IDaoPassager;
import air3il.commun.dto.DtoPassager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoPassager implements IDaoPassager {
	
	// Champs
	
	private final ManagerDao                managerDao;
	private final Map<Integer, DtoPassager>	mapPassagers;
	
	
	// Constructeur

    public DaoPassager(  ManagerDao managerDao ) {
		this.managerDao = managerDao;
		mapPassagers = managerDao.getMapPassagers();
	}

	
	// Actions
	
	@Override
	public void inserer(DtoPassager passager) {
		passager.setId( managerDao.getNextIdPassager()  );
		mapPassagers.put( passager.getId(), passager );
	}

	@Override
	public void modifier(DtoPassager passager) {
		mapPassagers.replace( passager.getId(), passager );
	}

	@Override
	public void supprimer(int idPassager) {
		mapPassagers.remove( idPassager );
	}

	@Override
	public DtoPassager retrouver(int idPassager) {
		return mapPassagers.get( idPassager );
	}

	@Override
	public List<DtoPassager> listerTout() {
		return new ArrayList<>(mapPassagers.values());
	}
	

}
