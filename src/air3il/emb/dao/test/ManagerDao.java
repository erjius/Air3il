package air3il.emb.dao.test;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dao.IManagerDao;
import air3il.commun.dto.DtoCompte;
import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoClient;
import air3il.commun.dto.DtoReservation;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ManagerDao implements IManagerDao {

    // Variables statiques
    private static final Logger logger = Logger.getLogger(ManagerDao.class.getName());

    // Champs
    private final Map<Class, Object> mapDaos = new HashMap<>();

    private final Map<Integer, DtoCompte> mapComptes = new LinkedHashMap<>();
    private final Map<Integer, DtoVol> mapVols = new LinkedHashMap<>();
    private final Map<Integer, DtoPays> mapPays = new LinkedHashMap<>();
    private final Map<Integer, DtoVille> mapVilles = new LinkedHashMap<>();
    private final Map<Integer, DtoClient> mapClients = new LinkedHashMap<>();
    private final Map<String, DtoReservation> mapReservations = new LinkedHashMap<>();
    

    private int lastIdCompte;
    private int lastIdVol;
    private int lastIdVille;
    private int lastIdPays;
    private int lastIdClient;

    // Constructeurs
    public ManagerDao() {
        initMData();
    }

    // Propriéts

    public Map<Integer, DtoCompte> getMapComptes() {
        return mapComptes;
    }

     public Map<Integer, DtoVol> getMapVols() {
        return mapVols;
    }
     
     public Map<Integer, DtoPays> getMapPays() {
        return mapPays;
    }
     
     public Map<Integer, DtoVille> getMapVilles() {
        return mapVilles;
    }
     
     public Map<Integer, DtoClient> getMapClients() {
        return mapClients;
    }

    public Map<String, DtoReservation> getMapReservations() {
        return mapReservations;
    }
     
     
     
    public int getNextIdCompte() {
        return ++lastIdCompte;
    }
    
    public int getNextIdVol() {
        return ++lastIdVol;
    }

    public int getNextIdVille() {
        return ++lastIdVille;
    }
    
    public int getNextIdPays() {
        return ++lastIdPays;
    }

    public int getNextIdClient() {
        return ++lastIdClient;
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

        // Comptes
        DtoCompte compte1 = new DtoCompte(1,"geek", "geek", "geek", "geek", "Administrateur");
        DtoCompte compte2 = new DtoCompte(2, "boss", "boss", "boss", "boss", "Hotesse");
        DtoCompte compte3 = new DtoCompte(3, "lambda", "lambda", "lambda", "lambda", "Hotesse");
        lastIdCompte = 3;

        mapComptes.put(compte1.getId(), compte1);
        mapComptes.put(compte2.getId(), compte2);
        mapComptes.put(compte3.getId(), compte3);

    }

}
