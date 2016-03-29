package air3il.commun.dto;

import java.io.Serializable;

public class DtoCompte implements Serializable {

    // Champs
    private int id;
    
    private String nom;
    
    private String prenom;

    private String login;

    private String motDePasse;

    private String type;

    // Propriétés
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Constructeurs
    public DtoCompte() {
        super();
    }

    public DtoCompte(int id, String nom, String prenom, String login, String motDePasse, String type) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motDePasse = motDePasse;
        this.type = type;
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
