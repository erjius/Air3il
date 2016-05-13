package air3il.gui.javafx.obs;

import air3il.commun.dto.DtoClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObsClient {

    // Données observables
    private final StringProperty propId = new SimpleStringProperty();
    private final StringProperty propNom = new SimpleStringProperty();
    private final StringProperty propPrenom = new SimpleStringProperty();
    private final StringProperty propTel = new SimpleStringProperty();
    private final StringProperty propEmail = new SimpleStringProperty();
    private final StringProperty propPrenom_recherche = new SimpleStringProperty();
    private final StringProperty propNom_recherche = new SimpleStringProperty();

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
    public StringProperty getPropPrenom_recherche() {
        return propPrenom_recherche;
    }
    public StringProperty getPropNom_recherche() {
        return propNom_recherche;
    }
    public StringProperty getPropEmail() {
        return propEmail;
    }
    public StringProperty getPropTel() {
        return propTel;
    }

    // Constructeurs
    public ObsClient() {
    }

    public ObsClient(ObsClient client) {
        copierDonnees(client);
    }

    public ObsClient(DtoClient dto) {
        copierDonnees(dto);
    }

    /* toString()
    @Override
    public String toString() {
        return propLibelle.get() + " " + propRole.get();
    }
*/
    // Actions
    public void copierDonnees(ObsClient g) {
        propId.set(g.getPropId().get());
        propNom.set(g.getPropNom().get());
        propPrenom.set(g.getPropPrenom().get());
        propPrenom_recherche.set(g.getPropPrenom_recherche().get());
        propNom_recherche.set(g.getPropNom_recherche().get());
        propEmail.set(g.getPropEmail().get());
        propTel.set(g.getPropTel().get());
    }

    public void copierDonnees(DtoClient dto) {
        propId.set(String.valueOf(dto.getId()));
        propNom.set(dto.getNom());
        propPrenom.set(dto.getPrenom());
//        propPrenom_recherche.set(dto.getPrenom_recherche());
//        propNom_recherche.set(dto.getNom_recherche());
        propEmail.set(dto.getEmail());
        propTel.set(dto.getNumtel());
    }

    public DtoClient creerDto() {
        DtoClient dto = new DtoClient();
        if (propId.get() != null) {
            dto.setId(Integer.parseInt(propId.get()));
        }
        dto.setNom(propNom.get());
        dto.setPrenom(propPrenom.get());
        dto.setEmail(propEmail.get());
        dto.setNumtel(propTel.get());
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
        ObsClient other = (ObsClient) obj;
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
