package air3il.emb.service.test;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dto.DtoCompte;
import air3il.commun.dto.DtoPays;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionAutorisation;
import air3il.commun.service.IManagerService;
import air3il.commun.service.IServiceCompte;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ManagerService implements IManagerService {

    // Logger
    private static final Logger logger = Logger.getLogger(ManagerService.class.getName());

    // Champs
    private final Lock verrou = new ReentrantLock();
    private final Map<Class, Object> mapServices = new HashMap<>();

    private final Map<Integer, DtoCompte> mapComptes = new LinkedHashMap<>();
    private final Map<Integer,DtoPays> mapPays = new LinkedHashMap<>();
   
    private DtoCompte compteConnecte = null;

    // Constructeur
    public ManagerService() {
        initMData();
    }

    public Map<Integer, DtoCompte> getMapComptes() {
        return mapComptes;
    }
    
    public Map<Integer, DtoPays> getMapPays(){
        return mapPays;
    }

    // Propriétés
    public DtoCompte getCompteConnecte() {
        return compteConnecte;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getService(Class<T> type) throws ExceptionAppli {

        // Recherche dans la map
        Object service = mapServices.get(type);

        if (service == null) {
            try {
                // Détermine le type à instancier
                Class<T> typeImpl;
                String nomImpl = type.getSimpleName().substring(1);
                String nomPackage = this.getClass().getPackage().getName();
                nomImpl = nomPackage + "." + nomImpl;
                typeImpl = (Class<T>) Class.forName(nomImpl);
                Constructor<T> constructor = typeImpl.getConstructor(new Class[]{ManagerService.class});

                // Instancie un objet et lui injecte les dépendances
                service = constructor.newInstance(new Object[]{this});

                // Ajoute l'objet à la map
                mapServices.put(type, service);

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                throw new ExceptionAppli(e);
            }

        }
        return (T) service;
    }

    // Actions accessibles via l'interface ImanagerService
    @Override
    public DtoCompte sessionUtilisateurOuvrir(String pseudo, String motDePasse) throws ExceptionAppli {
        try {
            sessionUtilisateurFermer();
            compteConnecte = getService(IServiceCompte.class).validerAuthentification(pseudo, motDePasse);
            return compteConnecte;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public void sessionUtilisateurFermer() throws ExceptionAppli {
        try {
            compteConnecte = null;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void threadAfterBegin() {
        verrou.lock();
    }

    @Override
    public void threadBeforeEnd() {
        verrou.unlock();
    }

    // Actions internes à la couche Service
    // Vérifie que le compte connecté a le rôle utilisateur (ou à défaut administrateur)
    public void verifierAutorisationUtilisateur() throws ExceptionAutorisation {
        if (compteConnecte == null
                || (!compteConnecte.getType().equals("HOTESSE")
                && !compteConnecte.getType().equals("ADMINISTRATEUR"))) {
            throw new ExceptionAutorisation();
        }
    }

    // Vérifie que le compte connecté a le rôle administrateur
    public void verifierAutorisationAdmin() throws ExceptionAutorisation {
        if (compteConnecte == null
                || !compteConnecte.getType().equals("ADMINISTRATEUR")) {
            throw new ExceptionAutorisation();
        }
    }

    // Vérifie que le compte connecte, soit a le rôle administrateur
    // soit a comme identifiant celui passé en paramètre
    public void verifierAutorisationAdminOuCompteConnecte(int idCompte) throws ExceptionAutorisation {
        if (compteConnecte == null
                || (!compteConnecte.getType().equals("ADMINISTRATEUR")
                && compteConnecte.getId() != idCompte)) {
            throw new ExceptionAutorisation();
        }
    }

    // Méthodes auxiliaires
    private void initMData() {

        // Comptes
        DtoCompte compte1 = new DtoCompte(1, "geek", "geek", "geek", "geek", "ADMINISTRATEUR");
        DtoCompte compte2 = new DtoCompte(2, "chef", "chef", "chef", "chef", "HOTESSE");
        DtoCompte compte3 = new DtoCompte(3,"job","job", "job", "job", "HOTESSE");

        mapComptes.put(compte1.getId(), compte1);
        mapComptes.put(compte2.getId(), compte2);
        mapComptes.put(compte3.getId(), compte3);
        
        // Pays
        DtoPays pays1 = new DtoPays(1, "");
        DtoPays pays2 = new DtoPays(2, "");
        DtoPays pays3 = new DtoPays(3, "");
        
        mapPays.put(pays1.getId(), pays1);
        mapPays.put(pays2.getId(), pays2);
        mapPays.put(pays3.getId(), pays3);
    }

}
