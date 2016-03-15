package air3il.emb.dao.test;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dao.IManagerDao;
import air3il.commun.dto.DtoCompte;
import air3il.commun.dto.DtoGroupe;
import air3il.commun.dto.DtoVol;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ManagerDao implements IManagerDao {

    // Variables statiques
    private static final Logger logger = Logger.getLogger(ManagerDao.class.getName());

    // Champs
    private final Map<Class, Object> mapDaos = new HashMap<>();

    private final Map<Integer, DtoGroupe> mapGroupes = new LinkedHashMap<>();
    private final Map<Integer, DtoCompte> mapComptes = new LinkedHashMap<>();
    private final Map<Integer, DtoVol> mapVols = new LinkedHashMap<>();

    private int lastIdGroupe;
    private int lastIdCompte;

    // Constructeurs
    public ManagerDao() {
        initMData();
    }

    // Propriéts
    public Map<Integer, DtoGroupe> getMapGroupes() {
        return mapGroupes;
    }

    public Map<Integer, DtoCompte> getMapComptes() {
        return mapComptes;
    }

     public Map<Integer, DtoVol> getMapVols() {
        return mapVols;
    }
     
    public int getNextIdGroupe() {
        return ++lastIdGroupe;
    }

    public int getNextIdCompte() {
        return ++lastIdCompte;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getDao(Class<T> type) {

        // Recherche dans la map
        Object dao = mapDaos.get(type);

        // Si pas trouvé dans la map
        if (dao == null) {
            try {

                // Détermine le type à instancier
                Class<T> typeImpl;
                String nomImpl = type.getSimpleName().substring(1);
                String nomPackage = this.getClass().getPackage().getName();
                nomImpl = nomPackage + "." + nomImpl;
                typeImpl = (Class<T>) Class.forName(nomImpl);
                Constructor<T> constructor = typeImpl.getConstructor(new Class[]{ManagerDao.class});

                // Instancie un objet et lui injecte les dépendances
                dao = constructor.newInstance(new Object[]{this});

                // Ajoute l'objet à la map
                mapDaos.put(type, dao);

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }

        return (T) dao;
    }

    // Actions
    @Override
    public void transactionBegin() {
    }

    @Override
    public void transactionCommit() {
    }

    @Override
    public void transactionRollback() {
    }

    @Override
    public void threadAfterBegin() {
    }

    @Override
    public void threadBeforeEnd() {
    }

    @Override
    public void close() {
    }

    // Méthodes auxiliaires
    private void initMData() {

        // Groupes
        DtoGroupe groupe1 = new DtoGroupe(1, "Utilisateur", "utilisateur");
        DtoGroupe groupe2 = new DtoGroupe(2, "Administrateur", "administrateur");
        lastIdGroupe = 2;

        mapGroupes.put(groupe1.getId(), groupe1);
        mapGroupes.put(groupe2.getId(), groupe2);

        // Comptes
        DtoCompte compte1 = new DtoCompte(1, "geek", "geek", "geek@3il.fr");
        DtoCompte compte2 = new DtoCompte(2, "boss", "boss", "boss@3il.fr");
        DtoCompte compte3 = new DtoCompte(3, "lambda", "lambda", "lambda@3il.fr");
        lastIdCompte = 3;

        compte1.getGroupes().add(groupe1);
        compte1.getGroupes().add(groupe2);
        compte2.getGroupes().add(groupe1);
        compte3.getGroupes().add(groupe1);

        mapComptes.put(compte1.getId(), compte1);
        mapComptes.put(compte2.getId(), compte2);
        mapComptes.put(compte3.getId(), compte3);

    }

}
