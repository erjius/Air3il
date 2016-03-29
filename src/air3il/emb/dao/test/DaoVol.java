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
    private final Map<Integer, DtoVille> mapVilles;

    // Constructeur
    public DaoVol(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapVols = managerDao.getMapVols();
        mapVilles = managerDao.getMapVilles();
    }

    // Actions
    @Override
    public DtoVol inserer(DtoVol vol) {
        vol.setId(managerDao.getNextIdVol());
        mapVols.put(vol.getId(), vol);
        return vol;
    }

    @Override
    public DtoVol modifier(DtoVol vol) {
        mapVols.replace(vol.getId(), vol);
        return vol;
    }

    @Override
    public void supprimer(int idVol) {
        mapVols.remove(idVol);
    }

    @Override
    public List<DtoVol> recherher(DtoVille ville_dep, DtoVille ville_arr, Date date_dep) {
        List<DtoVol> ListeVol = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Integer, DtoVol> KeyVols : mapVols.entrySet()) {
            if (this.mapVols.get(KeyVols).getville_dep().equals(ville_dep)
                    && this.mapVols.get(KeyVols).getville_arr().equals(ville_arr)
                    && this.mapVols.get(KeyVols).getDate_dep().equals(date_dep)) {
                ListeVol.add(this.mapVols.get(KeyVols));
            }
        }
        return ListeVol;
    }

    @Override
    public List<DtoVol> listerTout() {
        return new ArrayList<>(mapVols.values());
    }
   
}
