package air3il.gui.javafx.obs;

import java.util.List;

import air3il.commun.dto.DtoCompte;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObsCompte {

    // Données observables
    private final StringProperty propId = new SimpleStringProperty();
    private final StringProperty propNom = new SimpleStringProperty();
    private final StringProperty propPrenom = new SimpleStringProperty();
    private final StringProperty propLogin = new SimpleStringProperty();
    private final StringProperty propMotDePasse = new SimpleStringProperty();
    private final StringProperty propType = new SimpleStringProperty();

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

    public StringProperty getPropLogin() {
        return propLogin;
    }

    public StringProperty getPropMotDePasse() {
        return propMotDePasse;
    }

    public StringProperty getPropType() {
        return propType;
    }

    // Constructeurs
    public ObsCompte(ObsCompte compte) {
        copierDonnees(compte);
    }

    public ObsCompte(DtoCompte dto) {
        copierDonnees(dto);
    }

    // Actions
    public void copierDonnees(ObsCompte compte) {
        propId.set(compte.getPropId().get());
        propNom.set(compte.getPropNom().get());
        propPrenom.set(compte.getPropPrenom().get());
        propLogin.set(compte.getPropLogin().get());
        propMotDePasse.set(compte.getPropMotDePasse().get());
        propType.set(compte.getPropType().get());
    }

    public void copierDonnees(DtoCompte dto) {
        propId.set(String.valueOf(dto.getId()));
        propNom.set(dto.getNom());
        propPrenom.set(dto.getPrenom());
        propLogin.set(dto.getLogin());
        propMotDePasse.set(dto.getMotDePasse());
        propType.set(dto.getType());
    }

    public DtoCompte creerDto() {
        DtoCompte dto = new DtoCompte();
        if (propId.get() != null) {
            dto.setId(Integer.parseInt(propId.get()));
        }
        dto.setLogin(propLogin.get());
        dto.setMotDePasse(propMotDePasse.get());
        dto.setType(propType.get());
        return dto;
    }

    public boolean isInRole(String role) {
        if (role.equals(propType)) {
            return true;
        }
        return false;
    }

    // hashcode() et equals()
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((propId.get() == null) ? 0 : propId.get().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ObsCompte other = (ObsCompte) obj;
        if (propId.get() == null) {
            if (other.propId.get() != null) {
                return false;
            }
        } else if (!propId.get().equals(other.propId.get())) {
            return false;
        }
        return true;
    }
    
}
