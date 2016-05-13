package air3il.emb.service.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dto.DtoClient;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceClient;

public class ServiceClient implements IServiceClient {

    // Logger
    private static final Logger logger = Logger.getLogger(ServiceClient.class.getName());

    // Champs 
    private final ManagerService managerService;
    private final Map<Integer, DtoClient> mapClient;

    // Constructeur
    public ServiceClient(ManagerService managerService) {
        this.managerService = managerService;
        mapClient = managerService.getMapClient();
    }

    // Actions 
    @Override
    public DtoClient inserer(DtoClient client) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            verifierValiditeDonnees(client);
            if (mapClient.isEmpty()) {
                client.setId(1);
            } else {
                client.setId(Collections.max(mapClient.keySet()) + 1);
            }
            mapClient.put(client.getId(), client);
            return client;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoClient modifierClient(DtoClient client) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            verifierValiditeDonnees(client);
            mapClient.replace(client.getId(), client);
            return client;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public void supprimer(DtoClient client) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            mapClient.remove(client);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoClient rechercherClient (DtoClient client) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationUtilisateur();
            return mapClient.get(client);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public List<DtoClient> listerClient() throws ExceptionAppli {
        try {
            managerService.verifierAutorisationUtilisateur();;
            return new ArrayList<>(mapClient.values());
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    // Méthodes auxiliaires
    private void verifierValiditeDonnees(DtoClient dto) throws ExceptionAppli {

        StringBuilder message = new StringBuilder();

        if (dto.getNom() == null || dto.getNom().isEmpty()) {
            message.append("\nLe Nom est absent.");
        }
        if (dto.getPrenom() == null || dto.getPrenom().isEmpty()) {
            message.append("\nLe Prenom est absent.");
        }
    }

    @Override
    public DtoClient ajoutClient(DtoClient client) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
