package air3il.commun.dto;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DtoVol implements Serializable {

    // Champs
    private int id;

    private Date date_dep;
    
    private Date date_arr;

    private String etat;

    private Float prix_base;
    
    private DtoVille ville_dep;
    
    private DtoVille ville_arr;

    private List<DtoVol> vols = new ArrayList<>();

    // Propriétés
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(Date date_dep) {
        this.date_dep = date_dep;
    }

    public Date getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(Date date_arr) {
        this.date_arr = date_arr;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Float getPrix_base() {
        return prix_base;
    }

    public void setPrix_base(Float prix_base) {
        this.prix_base = prix_base;
    }

    public DtoVille getville_dep() {
        return ville_dep;
    }

    public void setville_dep(DtoVille ville_dep) {
        this.ville_dep = ville_dep;
    }

    public DtoVille getville_arr() {
        return ville_arr;
    }

    public void setville_arr(DtoVille id_ville_arr) {
        this.ville_arr = ville_arr;
    }

    public List<DtoVol> getVols() {
        return vols;
    }

    public void setVols(List<DtoVol> vols) {
        this.vols = vols;
    }

    // Constructeurs
    public DtoVol() {
        super();
    }

    public DtoVol(int id, Date date_dep, Date date_arr, String etat, Float prix_base, DtoVille id_ville_dep, DtoVille id_ville_arr) {
        super();
        this.id = id;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.etat = etat;
        this.prix_base = prix_base;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
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
        DtoVol other = (DtoVol) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

}
