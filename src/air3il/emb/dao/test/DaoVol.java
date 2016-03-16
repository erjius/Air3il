package air3il.emb.dao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import air3il.commun.dao.IDaoVol;
import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoVille;
//import air3il.emb.dao.test.DaoPays;
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
    public List<DtoVol> recherher(DtoVille ville_dep, DtoVille ville_arr, Date date_dep) {
        List<DtoVol> ListeVol = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Integer, DtoVol> KeyVols : mapVols.entrySet()) {
            if (this.mapVols.get(KeyVols).getville_dep() == ville_arr
                    && this.mapVols.get(KeyVols).getville_arr() == ville_arr
                    && this.mapVols.get(KeyVols).getDate_dep() == date_dep) {
                ListeVol.add(this.mapVols.get(KeyVols));
            }
        }
        return ListeVol;
    }

    @Override
    public List<DtoVol> listerTout() {
        return new ArrayList<>(mapVols.values());
    }

    @Override
    public List<DtoPays> listerPays() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*
            return new ArrayList<>(mapPays.values());
        */
    }

    @Override
    public List<DtoVille> listerVille(DtoPays pays) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*
            List<DtoVille> ListeVille = new ArrayList<>();
            for (Map.Entry<Integer, DtoVille> KeyVille : mapVilles.entrySet()) {
                if(this.mapVilles.get(KeyVilles).getPays() == pays){
                    ListVille.add(this.mapVilles.get(KeyVille));
                }
            }
            return ListeVille;
        */
    }

    
}
