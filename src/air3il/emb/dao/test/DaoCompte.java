package air3il.emb.dao.test;

import java.util.ArrayList;
import java.util.List;

import air3il.commun.dao.IDaoCompte;
import air3il.commun.dto.DtoCompte;
import java.util.Map;

public class DaoCompte implements IDaoCompte {

    // Champs
    private final ManagerDao managerDao;
    private final Map<Integer, DtoCompte> mapComptes;

    // Constructeur
    public DaoCompte(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapComptes = managerDao.getMapComptes();
    }

    // Actions
    @Override
    public void inserer(DtoCompte compte) {
        compte.setId(managerDao.getNextIdGroupe());
        mapComptes.put(compte.getId(), compte);
    }

    @Override
    public void modifier(DtoCompte compte) {
        mapComptes.replace(compte.getId(), compte);
    }

    @Override
    public void supprimer(int idCompte) {
        mapComptes.remove(idCompte);
    }

    @Override
    public DtoCompte retrouver(int idCompte) {
        return mapComptes.get(idCompte);
    }

    @Override
    public List<DtoCompte> listerTout() {
        return new ArrayList<>(mapComptes.values());
    }

    @Override
    public DtoCompte validerAuthentification(String pseudo, String motDePasse) {

        for (DtoCompte compte : mapComptes.values()) {
            if (compte.getPseudo().equals(pseudo)) {
                if (compte.getMotDePasse().equals(motDePasse)) {
                    return compte;
                }
                break;
            }
        }
        return null;
    }

    @Override
    public boolean verifierUnicitePseudo(String pseudo, int idCompte) {

        for (DtoCompte compte : mapComptes.values()) {
            if (compte.getPseudo().equals(pseudo)) {
                if (compte.getId() != idCompte) {
                    return false;
                }
            }
        }
        return true;
    }

}
