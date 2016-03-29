package air3il.gui.javafx.systeme;

import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.dto.DtoCompte;
import air3il.commun.exception.ExceptionAutorisation;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.groupe.ModelGroupe;
import air3il.gui.javafx.obs.ObsCompte;
import air3il.commun.service.IManagerService;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelSysteme {

    // Logger
    private static final Logger logger = Logger.getLogger(ModelSysteme.class.getName());

    // Données observables 
    // Vue connexion
    private final ObsCompte compteVue = new ObsCompte(new ArrayList<>());

    // Vue message 
    private final StringProperty propTitre = new SimpleStringProperty();
    private final StringProperty propMessage = new SimpleStringProperty();

    // Compte connecté
    private final ObjectProperty<ObsCompte> propCompteConnecte = new SimpleObjectProperty<>();

    // Autres champs
    private final ManagerGui managerGui;
    private final IManagerService managerService;

    // Propriétés
    public StringProperty getPropTitre() {
        return propTitre;
    }

    public StringProperty getPropMessage() {
        return propMessage;
    }

    public ObsCompte getCompteVue() {
        return compteVue;
    }

    public ObjectProperty<ObsCompte> getPropCompteConnecte() {
        return propCompteConnecte;
    }

    public ObsCompte getCompteConnecte() {
        return propCompteConnecte.get();
    }

    // Constructeur
    public ModelSysteme(ManagerGui managerGui) throws Exception {
        this.managerGui = managerGui;
        this.managerService = managerGui.getManagerService();

      //  compteVue.getPropLogin().set("geek");
      //  compteVue.getPropMotDePasse().set("geek");
    }

    // Actions
    public void ouvrirSessionUtilisateur() throws Exception {

        DtoCompte dto = managerService.sessionUtilisateurOuvrir(
                compteVue.getPropLogin().get(), compteVue.getPropMotDePasse().get());

        // Message de log
        String logMessage;
        if (dto == null) {
            logMessage = "Login ou mot de passe invalide : " + compteVue.getPropLogin().get() + " / " + compteVue.getPropMotDePasse().get();
        } else {
            logMessage = "Utilisateur connecté : " + dto.getId() + " " + dto.getLogin();
            logMessage += "\n  Roles :";
                logMessage += " " + dto.getType();
        }
        logger.log(Level.CONFIG, logMessage);

        if (dto == null) {
            throw new ExceptionAutorisation("Login ou mot de passe invalide.");
        } else {
            propCompteConnecte.set(new ObsCompte(dto, managerGui.getModel(ModelGroupe.class).getObsListGroupes()));
        }
    }

    public void fermerSessionUtilisateur() throws Exception {
        managerService.sessionUtilisateurFermer();
        propCompteConnecte.set(null);
        managerGui.reinitGui();
    }
}
