package air3il.emb.service.test;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dto.DtoCompte;
import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoReservation;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionAutorisation;
import air3il.commun.service.IManagerService;
import air3il.commun.service.IServiceCompte;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ManagerService implements IManagerService {

    // Logger
    private static final Logger logger = Logger.getLogger(ManagerService.class.getName());

    // Champs
    private final Lock verrou = new ReentrantLock();
    private final Map<Class, Object> mapServices = new HashMap<>();

    private final Map<Integer, DtoCompte> mapComptes = new LinkedHashMap<>();
    private final Map<Integer, DtoPays> mapPays = new LinkedHashMap<>();
    private final Map<Integer, DtoReservation> mapReservations = new LinkedHashMap<>();
    private final List<DtoVille> listVille=new ArrayList<>();
    private final List<DtoVol> listVol=new ArrayList<>();
    



    private DtoCompte compteConnecte = null;

    // Constructeur
    public ManagerService() {
        initMData();
    }
    public List<DtoVille> getListVille() {
        return listVille;
    }

    public Map<Integer, DtoCompte> getMapComptes() {
        return mapComptes;
    }

    public Map<Integer, DtoPays> getMapPays() {
        return mapPays;
    }

    public Map<Integer, DtoReservation> getMapReservations() {
        return mapReservations;
    }
    public List<DtoVol> getListVols() {
        return listVol;
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
        DtoCompte compte3 = new DtoCompte(3, "job", "job", "job", "job", "HOTESSE");

        mapComptes.put(compte1.getId(), compte1);
        mapComptes.put(compte2.getId(), compte2);
        mapComptes.put(compte3.getId(), compte3);
        
        mapPays.put(1, new DtoPays(1, "France"));
        mapPays.put(2, new DtoPays(2, "Vietnam"));
        mapPays.put(3, new DtoPays(3, "Royaume-Unis"));
        mapPays.put(4, new DtoPays(4, "Allemagne"));
        mapPays.put(5, new DtoPays(5, "Italie"));
        mapPays.put(6, new DtoPays(6, "Espagne"));
        mapPays.put(7, new DtoPays(7, "Etats-Unis"));
        
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        
        String dateD = "15/05/2016 15:15";  
        String dateA = "16/05/2016 16:16";
        String dateD11 = "28/06/2016 17:17";  
        String dateA22 = "29/06/2016 18:18";
        String dateD13 = "28/09/2016 19:21";  
        String dateA24 = "29/011/2016 21:26";
        
        try {
            Date dateD1 = simpleDateFormat.parse(dateD);
            Date dateA1 = simpleDateFormat.parse(dateA);
            Date dateD2 = simpleDateFormat.parse(dateD11);
            Date dateA2 = simpleDateFormat.parse(dateA22);
            Date dateD3 = simpleDateFormat.parse(dateD13);
            Date dateA3 = simpleDateFormat.parse(dateA24);
            
            DtoVille Paris = new DtoVille(1, "Paris", mapPays.get(1));
            DtoVille Saigon = new DtoVille(2, "Saigon", mapPays.get(2));
            DtoVille NY = new DtoVille(3, "New-York", mapPays.get(7));            
            DtoVille Lyon =new DtoVille(4, "Lyon", mapPays.get(1));
            DtoVille Hanoi = new DtoVille(5, "Hanoi", mapPays.get(2));
           
            listVille.add(Paris);
            listVille.add(Saigon);
            listVille.add(NY);
            listVille.add(Lyon);
            listVille.add(Hanoi);
            
            float prix = 150.15f;
            float prix1 = 850.5f;
            float prix2 = 750.46f;
            
            DtoVol Vol1 = new DtoVol(1, dateD1, dateA1, "Rien", prix, Paris, Saigon);
            DtoVol Vol2 = new DtoVol(2, dateD2, dateA2, "Rien", prix1, Saigon, Paris);
            DtoVol Vol3 = new DtoVol(3, dateD3, dateA3, "Rien", prix2, Paris, NY);
            listVol.add(Vol1);
            listVol.add(Vol2);
            listVol.add(Vol3);
        } catch (ParseException parseException) {
            System.err.println("merde");
        }


    }

}
