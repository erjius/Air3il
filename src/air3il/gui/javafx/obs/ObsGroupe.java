package air3il.gui.javafx.obs;

import air3il.commun.dto.DtoGroupe;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObsGroupe {

    // Données observables
    private final StringProperty propId = new SimpleStringProperty();
    private final StringProperty propLibelle = new SimpleStringProperty();
    private final StringProperty propRole = new SimpleStringProperty();

    // Accès aux données observables
    public StringProperty getPropId() {
        return propId;
    }

    public StringProperty getPropLibelle() {
        return propLibelle;
    }

    public StringProperty getPropRole() {
        return propRole;
    }

    // Constructeurs
    public ObsGroupe() {
    }

    public ObsGroupe(ObsGroupe groupe) {
        copierDonnees(groupe);
    }

    public ObsGroupe(DtoGroupe dto) {
        copierDonnees(dto);
    }

    // toString()
    @Override
    public String toString() {
        return propLibelle.get() + " " + propRole.get();
    }

    // Actions
    public void copierDonnees(ObsGroupe g) {
        propId.set(g.getPropId().get());
        propLibelle.set(g.getPropLibelle().get());
        propRole.set(g.getPropRole().get());
    }

    public void copierDonnees(DtoGroupe dto) {
        propId.set(String.valueOf(dto.getId()));
        propLibelle.set(dto.getLibelle());
        propRole.set(dto.getRole());
    }

    public DtoGroupe creerDto() {
        DtoGroupe dto = new DtoGroupe();
        if (propId.get() != null) {
            dto.setId(Integer.parseInt(propId.get()));
        }
        dto.setLibelle(propLibelle.get());
        dto.setRole(propRole.get());
        return dto;
    }

    // hashcode() et equals()
    @Override
    public int hashCode() {
        return propId.get() == null ? 0 : propId.get().hashCode();
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
        ObsGroupe other = (ObsGroupe) obj;
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
