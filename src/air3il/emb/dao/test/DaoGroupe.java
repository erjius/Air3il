package air3il.emb.dao.test;

import java.util.ArrayList;
import java.util.List;

import air3il.commun.dao.IDaoGroupe;
import air3il.commun.dto.DtoGroupe;
import java.util.Map;

public class DaoGroupe implements IDaoGroupe {

    // Champs
    private final ManagerDao managerDao;
    private final Map<Integer, DtoGroupe> mapGroupes;

    // Constructeur
    public DaoGroupe(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapGroupes = managerDao.getMapGroupes();
    }

    // Actions
    @Override
    public void inserer(DtoGroupe groupe) {
        groupe.setId(managerDao.getNextIdGroupe());
        mapGroupes.put(groupe.getId(), groupe);
    }

    @Override
    public void modifier(DtoGroupe groupe) {
        mapGroupes.replace(groupe.getId(), groupe);
    }

    @Override
    public void supprimer(int idGroupe) {
        mapGroupes.remove(idGroupe);
    }

    @Override
    public DtoGroupe retrouver(int idGroupe) {
        return mapGroupes.get(idGroupe);
    }

    @Override
    public List<DtoGroupe> listerTout() {
        return new ArrayList<>(mapGroupes.values());
    }

    @Override
    public List<DtoGroupe> listerPourCompte(int idCompte) {
        return null;
    }

}
