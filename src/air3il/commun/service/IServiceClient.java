package air3il.commun.service;

import air3il.commun.dto.DtoClient;
import java.util.List;

import air3il.commun.exception.ExceptionAppli;
import air3il.commun.service.IServiceClient;
import java.util.Date;

public interface IServiceClient {
    
    public List<DtoClient> listerClient() throws ExceptionAppli;
    
    public DtoClient ajoutClient(DtoClient client) throws ExceptionAppli;
    
    public DtoClient rechercherClient(DtoClient client) throws ExceptionAppli;
    
    public DtoClient modifierClient(DtoClient client)throws ExceptionAppli;
    
    
    public void supprimer(DtoClient client)throws ExceptionAppli;
    
    public DtoClient inserer(DtoClient client)throws ExceptionAppli;
    
    
    
    
    

}
