package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoCompte;

public interface IDaoCompte {

    
    /**
     * Pour ajouter un nouveau compte
     * @param compte
     * @return 
     */
    public DtoCompte inserer(DtoCompte compte);
    
    
    /**
     * Pour modifier un compte
     * @param compte
     * @return 
     */
    public DtoCompte modifier(DtoCompte compte);
    
    
    /**
     * Pour supprimer un compte
     * @param idCompte 
     */
    public void supprimer(int idCompte);

    
    /**
     * Pour trouver un compte à partir de son id
     * @param idCompte
     * @return 
     */
    public DtoCompte retrouver(int idCompte);

    
    /**
     * Pour obtenir la liste de tous les comptes de la base de donnée
     * @return 
     */
    public List<DtoCompte> listerTout();

    public DtoCompte validerAuthentification(String pseudo, String motDePasse);

    public boolean verifierUnicitePseudo(String pseudo, int idCompte);

}
