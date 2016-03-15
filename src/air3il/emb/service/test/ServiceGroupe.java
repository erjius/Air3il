package air3il.emb.service.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dto.DtoGroupe;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceGroupe;

public class ServiceGroupe implements IServiceGroupe {

    // Logger
    private static final Logger logger = Logger.getLogger(ServiceGroupe.class.getName());

    // Champs 
    private final ManagerService managerService;
    private final Map<Integer, DtoGroupe> mapGroupes;

    // Constructeur
    public ServiceGroupe(ManagerService managerService) {
        this.managerService = managerService;
        mapGroupes = managerService.getMapGroupes();
    }

    // Actions 
    @Override
    public DtoGroupe inserer(DtoGroupe groupe) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            verifierValiditeDonnees(groupe);
            if (mapGroupes.isEmpty()) {
                groupe.setId(1);
            } else {
                groupe.setId(Collections.max(mapGroupes.keySet()) + 1);
            }
            mapGroupes.put(groupe.getId(), groupe);
            return groupe;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoGroupe modifier(DtoGroupe groupe) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            verifierValiditeDonnees(groupe);
            mapGroupes.replace(groupe.getId(), groupe);
            return groupe;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public void supprimer(int idGroupe) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            mapGroupes.remove(idGroupe);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoGroupe retrouver(int idGroupe) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationUtilisateur();
            return mapGroupes.get(idGroupe);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public List<DtoGroupe> listerTout() throws ExceptionAppli {
        try {
            managerService.verifierAutorisationUtilisateur();;
            return new ArrayList<>(mapGroupes.values());
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    // Méthodes auxiliaires
    private void verifierValiditeDonnees(DtoGroupe dto) throws ExceptionAppli {

        StringBuilder message = new StringBuilder();

        if (dto.getLibelle() == null || dto.getLibelle().isEmpty()) {
            message.append("\nLe libellé est absent.");
        } else if (dto.getLibelle().length() < 3) {
            message.append("\nLe libellé est trop court.");
        } else if (dto.getLibelle().length() > 25) {
            message.append("\nLe libellé est trop long.");
        }

        if (dto.getRole() == null || dto.getRole().isEmpty()) {
            message.append("\nLe rôle est absent.");
        } else if (dto.getRole().length() < 3) {
            message.append("\nLe nom du rôle est trop court.");
        } else if (dto.getRole().length() > 25) {
            message.append("\nLe nom du rôle est trop long.");
        }

        if (message.length() > 0) {
            throw new ExceptionValidation(message.toString().substring(1));
        }
    }

}
