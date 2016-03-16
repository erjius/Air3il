package air3il.emb.dao.test;

import air3il.commun.dto.DtoPays;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import air3il.commun.dao.IDaoPays;
import java.util.Map;

public class DaoPays implements IDaoPays {

    // Champs
    private final ManagerDao managerDao;
    private final Map<Integer, DtoPays> mapPays;

    // Constructeur
    public DaoPays(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapPays = managerDao.getMapPays();
    }

    // Actions
    @Override
    public void inserer(DtoPays pays) {
        pays.setId(managerDao.getNextIdGroupe());
        mapPays.put(pays.getId(), pays);
    }

    @Override
    public void modifier(DtoPays pays) {
        mapPays.replace(pays.getId(), pays);
    }

    @Override
    public void supprimer(int idPays) {
        mapPays.remove(idPays);
    }

    @Override
    public DtoPays retrouver(int idPays) {
        return mapPays.get(idPays);
    }

    @Override
    public List<DtoPays> listerTout() {
        return new ArrayList<>(mapPays.values());
    }
    
}
