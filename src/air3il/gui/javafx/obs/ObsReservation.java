package air3il.gui.javafx.obs;

import air3il.commun.dto.DtoReservation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ARSENE
 */
public class ObsReservation {
    // Données observables

    private final StringProperty propId = new SimpleStringProperty();
    private final StringProperty propNom = new SimpleStringProperty();
    private final StringProperty propPrenom = new SimpleStringProperty();
    private final StringProperty propTel = new SimpleStringProperty();
    private final StringProperty propEmail = new SimpleStringProperty();

    // Accès aux données observables
    public StringProperty getPropId() {
        return propId;
    }

    public StringProperty getPropNom() {
        return propNom;
    }

    public StringProperty getPropPrenom() {
        return propPrenom;
    }

    public StringProperty getPropTel() {
        return propTel;
    }

    public StringProperty getPropEmail() {
        return propEmail;
    }

    // Constructeurs
    public ObsReservation() {

    }

    public ObsReservation(String id, String nom, String prenom, String tel, String email) {
        propId.set(id);
        propNom.set(nom);
        propPrenom.set(prenom);
        propTel.set(tel);
        propEmail.set(email);
    }

    public ObsReservation(ObsReservation reservation) {
        copierDonnees(reservation);
    }

    public ObsReservation(DtoReservation dto) {
        copierDonnees(dto);
    }

    // toString()
    @Override
    public String toString() {
        return propNom.get() + " " + propPrenom.get();
    }

    public void copierDonnees(ObsReservation p) {
        propId.set(p.getPropId().get());
        propNom.set(p.getPropNom().get());
        propPrenom.set(p.getPropPrenom().get());
        propTel.set(p.getPropTel().get());
        propEmail.set(p.getPropEmail().get());

    }

    public void copierDonnees(DtoReservation dto) {
        //propId.set( String.valueOf( dto.getId() ) );
        propNom.set(dto.getNom_pass());
        propPrenom.set(dto.getPrenom_pass());
        propTel.set(dto.getNumtel_pass());
        propEmail.set(dto.getEmail_pass());

    }

    public DtoReservation crerDto() {
        DtoReservation dtoReservation = new DtoReservation();

        dtoReservation.setNom_pass(propNom.get());
        dtoReservation.setPrenom_pass(propPrenom.get());
        dtoReservation.setNumtel_pass(propTel.get());
        dtoReservation.setEmail_pass(propEmail.get());

        return dtoReservation;
    }

}
