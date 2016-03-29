package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoClient;

public interface IDaoClient {

    public DtoClient inserer(DtoClient client);
    
    public DtoClient ajouter(String nom, String prenom, char tel, String email);

    public DtoClient modifier(DtoClient client);

    public void supprimer(int idClient);

    public DtoClient retrouver(int idClient);
    
    public List<DtoClient> rechercher (String nom, String prenom);

    public List<DtoClient> listerTout();
}