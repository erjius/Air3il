package air3il.emb.service.standard;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dao.IManagerDao;
import air3il.commun.dto.DtoCompte;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionAutorisation;
import air3il.commun.securite.Role;
import air3il.commun.service.IManagerService;
import air3il.commun.service.IServiceCompte;
import java.util.HashMap;

public class ManagerService implements IManagerService {

    // Logger
    private static final Logger logger = Logger.getLogger(ManagerService.class.getName());

    // Champs
    private final Map<Class, Object> mapServices = new HashMap<>();
    private final Lock verrou = new ReentrantLock();

    private IManagerDao managerDao;
    private DtoCompte compteConnecte;

    // Constructeur
    public ManagerService() {
        super();
        try {
            managerDao = new air3il.emb.dao.test.ManagerDao();
            logger.log(Level.CONFIG, "ManagerDao : {0}", managerDao.getClass().getName());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // Propriétés
    public IManagerDao getManagerDao() {
        return managerDao;
    }

    public DtoCompte getCompteConnecte() {
        return compteConnecte;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getService(Class<T> type) throws ExceptionAppli {

        // Recherche dans la map
        Object service = mapServices.get(type);

        // Si pas trouvé dans la map
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
    public void threadAfterBegin() {
        verrou.lock();
        managerDao.threadAfterBegin();
    }

    @Override
    public void threadBeforeEnd() {
        managerDao.threadBeforeEnd();
        verrou.unlock();
    }

    @Override
    public void close() {
        managerDao.close();
    }

    @Override
    public DtoCompte sessionUtilisateurOuvrir(String pseudo, String motDePasse) throws ExceptionAppli {
        try {
            sessionUtilisateurFermer();
            compteConnecte = getService(IServiceCompte.class).validerAuthentification(pseudo, motDePasse);
            return compteConnecte;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli("Echec du traitement demandé");
        }
    }

    @Override
    public void sessionUtilisateurFermer() throws ExceptionAppli {
        try {
            compteConnecte = null;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli("Echec du traitement demandé");
        }
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

}
