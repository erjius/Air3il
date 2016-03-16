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
public class DtoPlace implements Serializable {

    private int id;

    private char ref;
    
    private float coef;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getRef() {
        return ref;
    }

    public void setRef(char ref) {
        this.ref = ref;
    }

    public float getCoef() {
        return coef;
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }


    public DtoPlace() {
        super();
    }

    public DtoPlace(int id, char ref, float coef) {
        super();
        this.id = id;
        this.ref = ref;
        this.coef = coef;
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
        final DtoPlace other = (DtoPlace) obj;
        return this.id == other.id;
    }
    
    
}
