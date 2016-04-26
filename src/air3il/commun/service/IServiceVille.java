/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.commun.service;
import air3il.commun.dto.DtoVille;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import java.util.List;

/**
 *
 * @author projet
 */
public interface IServiceVille {
     public DtoVille inserer(DtoVille dto) throws ExceptionAppli, ExceptionValidation;

    public DtoVille modifier(DtoVille dto) throws ExceptionAppli, ExceptionValidation;

    public void supprimer(int id) throws ExceptionAppli;

    public DtoVille retrouver(int id) throws ExceptionAppli;
    
    public DtoVille chercher(String nom) throws ExceptionAppli;

    public List<DtoVille> listerTout() throws ExceptionAppli;
}
