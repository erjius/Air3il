package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoClient;

public interface IDaoClient {
    
    
    /**
     * Pour ajouter un nouveau client
     * @param client
     * @return 
     */
    public DtoClient inserer(DtoClient client);
    
    
    /**
     * Pour ajouter un nouveau client à partir de ses informations
     * @param nom
     * @param prenom
     * @param tel
     * @param email
     * @return 
     */
    public DtoClient ajouter(String nom, String prenom, char tel, String email);
    
    
    /**
     * Pour modifier un client
     * @param client
     * @return 
     */
    public DtoClient modifier(DtoClient client);
    
    
    /**
     * Pour supprimer un client
     * @param idClient 
     */
    public void supprimer(int idClient);
    
    
    /**
     * Pour trouver un client à partir de son id
     * @param idClient
     * @return 
     */
    public DtoClient retrouver(int idClient);
        
    
    /**
     * Pour obtenir la liste de tous les clients ayant les mêmes noms et prénoms
     * @param nom
     * @param prenom
     * @return La liste des clients
     */
    public List<DtoClient> rechercher (String nom, String prenom);

    
    /**
     * Pour obtenir la liste de tous les clients de la base de donnée
     * @return 
     */
    public List<DtoClient> listerTout();
}
