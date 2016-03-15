package air3il.gui.javafx.groupe;

import air3il.commun.dto.DtoGroupe;
import air3il.gui.javafx.EnumModeVue;
import static air3il.gui.javafx.EnumModeVue.CREER;
import static air3il.gui.javafx.EnumModeVue.MODIFIER;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsGroupe;
import air3il.commun.service.IServiceGroupe;
import java.util.Comparator;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelGroupe {

    // Données observables 
    private final ObservableList<ObsGroupe> obsListGroupes = FXCollections.observableArrayList(
            g -> new Observable[]{g.getPropLibelle(), g.getPropRole()}
    );

    private final ObsGroupe groupeVue = new ObsGroupe();

    // Objet courant
    private ObsGroupe groupeCourant;
    private EnumModeVue modeVue;

    // Autres champs
    private final IServiceGroupe serviceGroupe;

    // Propriétés
    public ObservableList<ObsGroupe> getObsListGroupes() {
        return obsListGroupes;
    }

    public ObsGroupe getGroupeVue() {
        return groupeVue;
    }

    // Constructeur
    public ModelGroupe(ManagerGui managerGui) throws Exception {
        serviceGroupe = managerGui.getManagerService().getService(IServiceGroupe.class);
        actualiserListe();
    }

    // Actualisations
    // Actualise la liste des memos
    public void actualiserListe() throws Exception {

        // Prépare l'initialisaiton de l'objet courant
        String idCourant = null;
        if (groupeCourant != null) {
            idCourant = groupeCourant.getPropId().get();
        }

        // Actualise la liste
        obsListGroupes.clear();
        for (DtoGroupe dto : serviceGroupe.listerTout()) {
            ObsGroupe groupe = new ObsGroupe(dto);
            if (groupe.getPropId().get().equals(idCourant)) {
                groupeCourant = groupe;
            }
            obsListGroupes.add(groupe);
        }
        trierListe();
    }

    // Actions
    public void preparerAjouter() {
        modeVue = CREER;
        groupeVue.copierDonnees(new ObsGroupe());
    }

    public void preparerModifier(ObsGroupe groupe) {
        modeVue = MODIFIER;
        groupeCourant = groupe;
        groupeVue.copierDonnees(groupe);
    }

    public void validerMiseAJour() throws Exception {

        // Crée un objet contenant le données pour la mise à jour
        DtoGroupe dto = groupeVue.creerDto();

        // Effectue la mise à jour
        if (modeVue == CREER) {
            dto = serviceGroupe.inserer(dto);
            groupeCourant = new ObsGroupe(dto);
            obsListGroupes.add(groupeCourant);
        }
        if (modeVue == MODIFIER) {
            dto = serviceGroupe.modifier(dto);
            groupeCourant.copierDonnees(dto);
        }

        // Trie la liste
        trierListe();
    }

    public void supprimer(ObsGroupe groupe) throws Exception {
        int id = Integer.parseInt(groupe.getPropId().get());
        serviceGroupe.supprimer(id);
        obsListGroupes.remove(groupe);
    }

    // Méthodes auxiliaires
    private void trierListe() {
        FXCollections.sort(obsListGroupes,
                (Comparator<ObsGroupe>) (g1, g2) -> {
                    return (g1.getPropLibelle().get().toUpperCase().compareTo(g2.getPropLibelle().get().toUpperCase()));
                });
    }

}
