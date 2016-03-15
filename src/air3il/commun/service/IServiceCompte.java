package air3il.commun.service;

import java.util.List;

import air3il.commun.dto.DtoCompte;
import air3il.commun.exception.ExceptionAppli;

public interface IServiceCompte {

    public DtoCompte inserer(DtoCompte dto) throws ExceptionAppli;

    public DtoCompte modifier(DtoCompte dto) throws ExceptionAppli;

    public void supprimer(int idCompte) throws ExceptionAppli;

    public DtoCompte retrouver(int idCompte) throws ExceptionAppli;

    public List<DtoCompte> listerTout() throws ExceptionAppli;

    public DtoCompte validerAuthentification(String pseudo, String motDePasse) throws ExceptionAppli;

}
