/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.commun.service;

import air3il.commun.dto.DtoPays;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import java.util.List;

/**
 *
 * @author projet
 */
public interface IServicePays {
    
    public DtoPays inserer(DtoPays dto) throws ExceptionAppli, ExceptionValidation;

    public DtoPays modifier(DtoPays dto) throws ExceptionAppli, ExceptionValidation;

    public void supprimer(int idCompte) throws ExceptionAppli;

    public DtoPays retrouver(int idPays) throws ExceptionAppli;
    
    public DtoPays chercher(String nom) throws ExceptionAppli;

    public List<DtoPays> listerTout() throws ExceptionAppli;

}
