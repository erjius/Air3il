package air3il.emb.service.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dto.DtoCompte;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceCompte;

public class ServiceCompte implements IServiceCompte {

    // Logger
    private static final Logger logger = Logger.getLogger(ServiceCompte.class.getName());

    // Champs 
    private final ManagerService managerService;

    private final Map<Integer, DtoCompte> mapComptes;

    // Constructeur
    public ServiceCompte(ManagerService managerService) {
        this.managerService = managerService;
        mapComptes = managerService.getMapComptes();
    }

    // Actions 
    @Override
    public DtoCompte inserer(DtoCompte compte) throws ExceptionAppli {

        try {
            managerService.verifierAutorisationAdmin();
            compte.setId(0);
            verifierValiditeDonnees(compte);
            compte.setId(Collections.max(mapComptes.keySet()) + 1);
            if (mapComptes.isEmpty()) {
                compte.setId(1);
            } else {
                compte.setId(Collections.max(mapComptes.keySet()) + 1);
            }
            mapComptes.put(compte.getId(), compte);
            return compte;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoCompte modifier(DtoCompte compte) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            verifierValiditeDonnees(compte);
            mapComptes.replace(compte.getId(), compte);
            return compte;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public void supprimer(int idCompte) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            if (managerService.getCompteConnecte().getId() == idCompte) {
                throw new ExceptionAppli("Vous ne pouvez pas supprimer le compte avec lequel vous êtes connecté !");
            }

            mapComptes.remove(idCompte);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoCompte retrouver(int idCompte) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            return mapComptes.get(idCompte);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public List<DtoCompte> listerTout() throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            return new ArrayList<>(mapComptes.values());
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoCompte validerAuthentification(String pseudo, String motDePasse) throws ExceptionAppli {
        DtoCompte compteAuthentifie = null;
        for (DtoCompte compte : managerService.getMapComptes().values()) {
            if (compte.getPseudo().equals(pseudo)) {
                if (compte.getMotDePasse().equals(motDePasse)) {
                    compteAuthentifie = compte;
                }
                break;
            }
        }
        return compteAuthentifie;
    }

    // Méthodes auxiliaires
    private void verifierValiditeDonnees(DtoCompte dto) throws ExceptionAppli {

        StringBuilder message = new StringBuilder();

        if (dto.getPseudo() == null || dto.getPseudo().isEmpty()) {
            message.append("\nLe pseudo est absent.");
        } else if (dto.getPseudo().length() < 3) {
            message.append("\nLe pseudo est trop court.");
        } else if (dto.getPseudo().length() > 25) {
            message.append("\nLe pseudo est trop long.");
        } else {
            boolean pseudoDejaPresent = false;
            for (DtoCompte c : mapComptes.values()) {
                if (c.getPseudo().equals(dto.getPseudo()) && c.getId() != dto.getId()) {
                    pseudoDejaPresent = true;
                    break;
                }
            }
            if (pseudoDejaPresent) {
                message.append("\nLe pseudo ").append(dto.getPseudo()).append(" est déjà utilisé.");
            }
        }

        if (dto.getMotDePasse() == null || dto.getMotDePasse().isEmpty()) {
            message.append("\nLe mot de passe est absent.");
        } else if (dto.getMotDePasse().length() < 3) {
            message.append("\nLe mot de passe est trop court.");
        } else if (dto.getMotDePasse().length() > 25) {
            message.append("\nLe mot de passe est trop long.");
        }

        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            message.append("\nL'adresse e-mail est absente.");
        }

        if (message.length() > 0) {
            throw new ExceptionValidation(message.toString().substring(1));
        }
    }

}
