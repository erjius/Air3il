package air3il.commun.dao;

import air3il.commun.dto.DtoPays;
import java.util.List;

import air3il.commun.dto.DtoVille;

public interface IDaoVille {

    
    /**
     * Pour ajouter une ville
     * @param ville
     * @return 
     */
    public DtoVille inserer(DtoVille ville);

    
    /**
     * Pour modifier une ville
     * @param ville
     * @return 
     */
    public DtoVille modifier(DtoVille ville);

    
    /**
     * Pour supprimer une ville
     * @param idVille 
     */
    public void supprimer(int idVille);

    
    /**
     * Pour trouver une ville à partir de son id
     * @param idVille
     * @return 
     */
    public DtoVille retrouver(int idVille);

    
    /**
     * Pour obtenir la liste de toutes les ville de la base de donnée
     * @return 
     */
    public List<DtoVille> listerTout();
    
    
    /**
     * Pour obtenir la liste de toutes les villes d'un pays
     * @param pays
     * @return 
     */
    public List<DtoVille> listerVilleParPays (DtoPays pays);
       
}
