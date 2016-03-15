package air3il.emb.dao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import air3il.commun.dao.IDaoVol;
import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoVille;
import java.util.Map;

public class DaoVol implements IDaoVol {

    // Champs
    private final ManagerDao managerDao;
    private final Map<Integer, DtoVol> mapVols;

    // Constructeur
    public DaoVol(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapVols = managerDao.getMapVols();
    }

    // Actions
    @Override
    public void inserer(DtoVol vol) {
        vol.setId(managerDao.getNextIdGroupe());
        mapVols.put(vol.getId(), vol);
    }

    @Override
    public void modifier(DtoVol vol) {
        mapVols.replace(vol.getId(), vol);
    }

    @Override
    public void supprimer(int idVol) {
        mapVols.remove(idVol);
    }

    @Override
    public DtoVol recherher(DtoVille ville_dep, DtoVille ville_arr, Date date_dep) {
        for (Map.Entry<Integer, DtoVol> mapVols : mapVols.entrySet()) {
            if (this.mapVols.containsValue(ville_dep) && this.mapVols.containsValue(ville_arr) && this.mapVols.containsValue(date_dep)) {
                
            }
            
        }
        return mapVols.get(date_dep);
    }

    @Override
    public List<DtoVol> listerTout() {
        return new ArrayList<>(mapVols.values());
    }

    
}
