package air3il.emb.dao.test;

import air3il.commun.dto.DtoVille;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import air3il.commun.dao.IDaoVille;
import air3il.commun.dto.DtoPays;
import java.util.Map;

public class DaoVille implements IDaoVille {

    // Champs
    private final ManagerDao managerDao;
    private final Map<Integer, DtoVille> mapVilles;

    // Constructeur
    public DaoVille(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapVilles = managerDao.getMapVilles();
    }

    // Actions
    @Override
    public void inserer(DtoVille ville) {
        ville.setId(managerDao.getNextIdGroupe());
        mapVilles.put(ville.getId(), ville);
    }

    @Override
    public void modifier(DtoVille ville) {
        mapVilles.replace(ville.getId(), ville);
    }

    @Override
    public void supprimer(int idVille) {
        mapVilles.remove(idVille);
    }

    @Override
    public DtoVille retrouver(int idVille) {
        return mapVilles.get(idVille);
    }

    @Override
    public List<DtoVille> listerTout() {
        return new ArrayList<>(mapVilles.values());
    }
    
    @Override
    public List<DtoVille> listerVilleParPays(DtoPays pays) {
        List<DtoVille> ListeVille = new ArrayList<>();
        for (Map.Entry<Integer, DtoVille> KeyVille : mapVilles.entrySet()) {
            if(this.mapVilles.get(KeyVille).getPays() == pays){
                ListeVille.add(this.mapVilles.get(KeyVille));
            }
        }
        return ListeVille;
    }

}
