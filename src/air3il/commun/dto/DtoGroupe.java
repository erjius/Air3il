package air3il.commun.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DtoGroupe implements Serializable {

    // Champs
    private int id;

    private String libelle;

    private String role;

    // Propriétés
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Constructeurs
    public DtoGroupe() {
        super();
    }

    public DtoGroupe(int id, String libelle, String role) {
        super();
        this.id = id;
        this.libelle = libelle;
        this.role = role;
    }

    // hashcode() et equals()
    @Override
    public int hashCode() {
        return id;
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
        DtoGroupe other = (DtoGroupe) obj;
        return id == other.id;
    }

}
