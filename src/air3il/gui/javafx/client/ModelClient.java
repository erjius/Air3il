package air3il.gui.javafx.client;

import air3il.commun.dto.DtoClient;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceClient;
import air3il.gui.javafx.EnumModeVue;
import static air3il.gui.javafx.EnumModeVue.CREER;
import static air3il.gui.javafx.EnumModeVue.MODIFIER;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsClient;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ARSENE
 */
public final class ModelClient {

    // Données observables 
    private final ObservableList<ObsClient> obsListClient = FXCollections.observableArrayList(
            p -> new Observable[]{p.getPropNom(), p.getPropPrenom(), p.getPropTel(), p.getPropEmail()}
    );

    private final ObsClient clientvue = new ObsClient();

    // Objet courant
    private ObsClient ClientCourant;
    private EnumModeVue modeVue;

    // Autres champs
    private final IServiceClient serviceClient;

    // Propriétés
    public ObservableList<ObsClient> getObsListClient() {
        return obsListClient;
    }

    public ObsClient getClientVue() {
        return clientvue;
    }

    // Constructeur
    public ModelClient(ManagerGui managerGui) throws Exception {
        serviceClient = managerGui.getManagerService().getService(IServiceClient.class);
        actualiserListe();
    }

    // Actualisations
    public void actualiserListe() throws Exception {

        // Prépare l'initialisaiton de l'objet courant
        String idCourant = null;
        if (ClientCourant != null) {
            idCourant = ClientCourant.getPropId().get();
        }

        // Actualise la liste
        obsListClient.clear();
        for (DtoClient dto : serviceClient.listerClient()) {
            ObsClient client = new ObsClient(dto);
            if (client.getPropId().get().equals(idCourant)) {
                ClientCourant = client;
            }
            obsListClient.add(client);
        }
        trierListe();
    }

    // Actions
    public void ajouter() throws ExceptionAppli {
        modeVue = CREER;
        DtoClient dto;
        ClientCourant = null;
        try {
            dto = serviceClient.inserer(clientvue.creerDto());
            ClientCourant = new ObsClient(dto);
        } catch (ExceptionAppli ex) {
            Logger.getLogger(ModelClient.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        // Test si c'est un ajout ou une modificaiton
        if (modeVue == CREER) {
            obsListClient.add(new ObsClient(dto));
        }

        // Trie la list
        trierListe();
    }

    public void preparerModifier(ObsClient client) {
        modeVue = MODIFIER;
        ClientCourant = client;
        clientvue.copierDonnees(client);
    }

    public void ValiderMiseAJour() throws Exception {

        String nom = clientvue.getPropNom().get();
        String prenom = clientvue.getPropPrenom().get();
        String tel = clientvue.getPropTel().get();
        String email = clientvue.getPropEmail().get();

        StringBuilder message = new StringBuilder();
        if (nom == null || nom.isEmpty()) {
            message.append("\nLe nom ne doit pas être vide.");
        } else if (nom.length() > 25) {
            message.append("\nLe nom est trop long.");
        }
        if (prenom == null || prenom.isEmpty()) {
            message.append("\nLe prénom ne doit pas être vide.");
        } else if (prenom.length() > 25) {
            message.append("\nLe prénom est trop long.");
        }

        if (message.length() > 0) {
            throw new ExceptionValidation(message.toString().substring(1));
        }
    }

    public void supprimer(ObsClient client) throws Exception {

        serviceClient.supprimer(null);
        obsListClient.remove(client);
    }

    //stockage  des passagers dans un tableau
    public void stocker_tableau() {
        int id = Integer.parseInt(clientvue.getPropId().get());
        String nom = clientvue.getPropNom().get();
        String prenom = clientvue.getPropPrenom().get();
        String tel = clientvue.getPropTel().get();
        String email = clientvue.getPropEmail().get();
    }

    // Méthodes auxiliaires
    private void trierListe() {
        FXCollections.sort(obsListClient,
                (Comparator<ObsClient>) (p1, p2) -> {
                    int lastCmp = p1.getPropNom().get().toUpperCase().compareTo(p2.getPropNom().get().toUpperCase());
                    return (lastCmp != 0 ? lastCmp : p1.getPropPrenom().get().toUpperCase().compareTo(p2.getPropPrenom().get()));
                });
    }

    void modifier(ObsClient rowValue) {
        try {
            serviceClient.modifierClient(rowValue.creerDto());
        } catch (ExceptionAppli ex) {
            Logger.getLogger(ModelClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
