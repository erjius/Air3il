/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.commun.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author antoine
 */
public class DtoReservation implements Serializable {

    private DtoVol vol;
    
    private DtoPlace place;
    
    private DtoClient client;
    
    private DtoCompte utilisateur;

    private String nom_pass;
    
    private String prenom_pass;
    
    private float prix;
    
    private char numtel_pass;
    
    private String email_pass;

    public DtoVol getVol() {
        return vol;
    }

    public void setVol(DtoVol vol) {
        this.vol = vol;
    }

    public DtoPlace getPlace() {
        return place;
    }

    public void setPlace(DtoPlace place) {
        this.place = place;
    }

    public DtoClient getClient() {
        return client;
    }

    public void setClient(DtoClient client) {
        this.client = client;
    }

    public DtoCompte getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(DtoCompte utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNom_pass() {
        return nom_pass;
    }

    public void setNom_pass(String nom_pass) {
        this.nom_pass = nom_pass;
    }

    public String getPrenom_pass() {
        return prenom_pass;
    }

    public void setPrenom_pass(String prenom_pass) {
        this.prenom_pass = prenom_pass;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public char getNumtel_pass() {
        return numtel_pass;
    }

    public void setNumtel_pass(char numtel_pass) {
        this.numtel_pass = numtel_pass;
    }

    public String getEmail_pass() {
        return email_pass;
    }

    public void setEmail_pass(String email_pass) {
        this.email_pass = email_pass;
    }

    public DtoReservation() {
        super();
    }

    public DtoReservation(DtoVol vol, DtoPlace place, DtoClient client, DtoCompte utilisateur, String nom_pass, String prenom_pass, float prix) {
        super();
        this.vol = vol;
        this.place = place;
        this.client = client;
        this.utilisateur = utilisateur;
        this.nom_pass = nom_pass;
        this.prenom_pass = prenom_pass;
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.vol);
        hash = 89 * hash + Objects.hashCode(this.place);
        return hash;
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
        final DtoReservation other = (DtoReservation) obj;
        if (!Objects.equals(this.vol, other.vol)) {
            return false;
        }
        if (!Objects.equals(this.place, other.place)) {
            return false;
        }
        return true;
    }

    
    
    
}