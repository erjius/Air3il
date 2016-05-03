package air3il.commun.service;

import air3il.commun.dto.DtoCompte;
import air3il.commun.exception.ExceptionAppli;

public interface IManagerService {

    public <T> T getService(Class<T> type) throws ExceptionAppli;

    public DtoCompte sessionUtilisateurOuvrir(String pseudo, String motDePasse) throws ExceptionAppli;
    
    public DtoCompte getCompteConnecte();
     
    public void sessionUtilisateurFermer() throws ExceptionAppli;

    public void threadAfterBegin();

    public void threadBeforeEnd();

    public void close();

}
