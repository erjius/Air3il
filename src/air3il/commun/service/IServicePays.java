/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.commun.service;

import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVille;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import java.util.List;

/**
 *
 * @author projet
 */
public interface IServicePays {
    
    public DtoPays insererPays(DtoPays dto) throws ExceptionAppli, ExceptionValidation;

    public DtoPays modifierPays(DtoPays dto) throws ExceptionAppli, ExceptionValidation;

    public void supprimerPays(int idPays) throws ExceptionAppli;

    public DtoPays retrouverPays(int idPays) throws ExceptionAppli;
    
    public DtoPays chercherPays(String nom) throws ExceptionAppli;

    public List<DtoPays> listerToutPays() throws ExceptionAppli;
    
    public DtoVille insererVille(DtoVille dto) throws ExceptionAppli, ExceptionValidation;

    public DtoVille modifierVille(DtoVille dto) throws ExceptionAppli, ExceptionValidation;

    public void supprimerVille(int id) throws ExceptionAppli;

    public DtoVille retrouverVille(int id) throws ExceptionAppli;
    
    public DtoVille chercherVille(String nom) throws ExceptionAppli;

    public List<DtoVille> listerToutVille() throws ExceptionAppli;

}
