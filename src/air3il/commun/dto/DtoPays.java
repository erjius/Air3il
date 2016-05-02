/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.commun.dto;

import java.io.Serializable;

/**
 *
 * @author antoine
 */
public class DtoPays implements Serializable {

    private int id;

    private String nom;

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

    public DtoPays() {
        super();
    }

    public DtoPays(int id, String nom) {
        super();
        this.id = id;
        this.nom = nom;
    }

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
        final DtoPays other = (DtoPays) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return nom;
    }
    
    
}
