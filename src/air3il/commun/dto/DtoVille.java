/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.commun.dto;

/**
 *
 * @author antoine
 */
public class DtoVille {

    private int id;

    private String nom;

    private DtoPays pays;

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

    public DtoPays getPays() {
        return pays;
    }

    public void setPays(DtoPays pays) {
        this.pays = pays;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public DtoVille(int id, String nom, DtoPays pays) {
        super();
        this.id = id;
        this.nom = nom;
        this.pays = pays;
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
        final DtoVille other = (DtoVille) obj;
        return this.id == other.id;
    }
}
