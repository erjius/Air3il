package air3il.commun.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DtoCompte implements Serializable {

    // Champs
    private int id;

    private String pseudo;

    private String motDePasse;

    private String email;

    private List<DtoGroupe> groupes = new ArrayList<>();

    // Propriétés
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DtoGroupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<DtoGroupe> groupes) {
        this.groupes = groupes;
    }

    public boolean isInRole(String role) {

        for (DtoGroupe groupe : groupes) {
            if (role.equals(groupe.getRole())) {
                return true;
            }
        }
        return false;
    }

    // Constructeurs
    public DtoCompte() {
        super();
    }

    public DtoCompte(int id, String pseudo, String motDePasse, String email) {
        super();
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.email = email;
    }

    // hashcode() et equals()
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        DtoCompte other = (DtoCompte) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

}
