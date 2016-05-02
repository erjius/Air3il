
package air3il.gui.javafx.reservation;

import air3il.commun.dto.DtoReservation;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServiceReservation;
import air3il.gui.javafx.EnumModeVue;
import static air3il.gui.javafx.EnumModeVue.CREER;
import static air3il.gui.javafx.EnumModeVue.MODIFIER;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsReservation;
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
public final class ModelReservation {

    // Données observables 
    private final ObservableList<ObsReservation> obsListReservations = FXCollections.observableArrayList(
            p -> new Observable[]{p.getPropNom(), p.getPropPrenom(), p.getPropTel(), p.getPropEmail()}
    );

    private final ObsReservation reservationVue = new ObsReservation("99", "", "", "", "");

    // Objet courant
    private ObsReservation reservationCourante;
    private EnumModeVue modeVue;

    // Autres champs
    private final IServiceReservation serviceReservation;

    // Propriétés
    public ObservableList<ObsReservation> getObsListReservations() {
        return obsListReservations;
    }

    public ObsReservation getReservationVue() {
        return reservationVue;
    }

    // Constructeur
    public ModelReservation(ManagerGui managerGui) throws Exception {
        serviceReservation = managerGui.getManagerService().getService(IServiceReservation.class);
        actualiserListe();
    }

    // Actualisations
    public void actualiserListe() throws Exception {

        // Prépare l'initialisaiton de l'objet courant
        String idCourant = null;
        if (reservationCourante != null) {
            idCourant = reservationCourante.getPropId().get();
        }

        // Actualise la liste
        obsListReservations.clear();
        for (DtoReservation dto : serviceReservation.listerTout()) {
            ObsReservation reservation = new ObsReservation(dto);
            if (reservation.getPropId().get().equals(idCourant)) {
                reservationCourante = reservation;
            }
            obsListReservations.add(reservation);
        }
        trierListe();
    }

    // Actions
    public void preparerAjouter() {
        modeVue = CREER;
        DtoReservation dto;
        reservationCourante = null;
        try {
            dto = serviceReservation.inserer(reservationVue.crerDto());
            reservationCourante = new ObsReservation(dto);
        } catch (ExceptionAppli ex) {
            Logger.getLogger(ModelReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preparerModifier(ObsReservation reservation) {
        modeVue = MODIFIER;
        reservationCourante = reservation;
        reservationVue.copierDonnees(reservation);
    }

    public void ValiderMiseAJour() throws Exception {

        String nom = reservationVue.getPropNom().get();
        String prenom = reservationVue.getPropPrenom().get();

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

        // Test si c'est un ajout ou une modificaiton
        if (modeVue == CREER) {
            obsListReservations.add(reservationCourante);
        }

        // Trie la list
        trierListe();
    }

    public void supprimer(ObsReservation reservation) throws Exception {
        int id = Integer.parseInt(reservation.getPropId().get());
        obsListReservations.remove(reservation);
    }
    
    

    

    // Méthodes auxiliaires
    private void trierListe() {
        FXCollections.sort(obsListReservations,
                (Comparator<ObsReservation>) (p1, p2) -> {
                    int lastCmp = p1.getPropNom().get().toUpperCase().compareTo(p2.getPropNom().get().toUpperCase());
                    return (lastCmp != 0 ? lastCmp : p1.getPropPrenom().get().toUpperCase().compareTo(p2.getPropPrenom().get()));
                });
    }

}
