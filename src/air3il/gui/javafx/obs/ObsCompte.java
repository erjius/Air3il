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
    private final StringProperty propLogin = new SimpleStringProperty();
    private final StringProperty propMotDePasse = new SimpleStringProperty();
    private final StringProperty propType = new SimpleStringProperty();

    private final ObservableList<ItemGroupe> obsListGroupes = FXCollections.observableArrayList();

    // Accès aux données observables
    public StringProperty getPropId() {
        return propId;
    }

    public StringProperty getPropLogin() {
        return propLogin;
    }

    public StringProperty getPropMotDePasse() {
        return propMotDePasse;
    }

    public ObservableList<ItemGroupe> getObsListGroupes() {
        return obsListGroupes;
    }

    public StringProperty getPropType() {
        return propType;
    }

    // Constructeurs
    public ObsCompte(List<ObsGroupe> groupes) {
        for (ObsGroupe groupe : groupes) {
            obsListGroupes.add(new ItemGroupe(groupe, false));
        }
    }

    public ObsCompte(ObsCompte compte) {
        copierDonnees(compte);
    }

    public ObsCompte(DtoCompte dto, List<ObsGroupe> groupes) {
        copierDonnees(dto, groupes);
    }

    // Actions
    public void copierDonnees(ObsCompte compte) {
        propId.set(compte.getPropId().get());
        propLogin.set(compte.getPropLogin().get());
        propMotDePasse.set(compte.getPropMotDePasse().get());
        propType.set(compte.getPropType().get());
        obsListGroupes.clear();
        for (ItemGroupe item : compte.getObsListGroupes()) {
            obsListGroupes.add(new ItemGroupe(item));
        }
    }

    public void copierDonnees(DtoCompte dto, List<ObsGroupe> groupes) {
        propId.set(String.valueOf(dto.getId()));
        propLogin.set(dto.getLogin());
        propMotDePasse.set(dto.getMotDePasse());
        propType.set(dto.getType());
        obsListGroupes.clear();
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

        for (ItemGroupe item : obsListGroupes) {
            if (role.equals(item.getGroupe().getPropRole().get()) && item.getPropChoisi().get()) {
                return true;
            }
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

    // Classe auxiliaire
    public static class ItemGroupe {

        // Champs
        private final ObsGroupe groupe;
        private final BooleanProperty propChoisi;

        // Propriétés
        public ObsGroupe getGroupe() {
            return groupe;
        }

        public void setChoisi(boolean choisi) {
            propChoisi.set(choisi);
        }

        public BooleanProperty getPropChoisi() {
            return propChoisi;
        }

        @Override
        public String toString() {
            return groupe.getPropLibelle().get();
        }

        // Constructeurs
        public ItemGroupe(ObsGroupe groupe, boolean present) {
            this.groupe = groupe;
            this.propChoisi = new SimpleBooleanProperty(present);
        }

        public ItemGroupe(ItemGroupe item) {
            this.groupe = item.getGroupe();
            this.propChoisi = new SimpleBooleanProperty(item.getPropChoisi().get());
        }
    }

}
