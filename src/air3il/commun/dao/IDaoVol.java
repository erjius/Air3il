package air3il.commun.dao;

import java.util.List;
import java.util.Date;

import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoPays;

public interface IDaoVol {

    
    /**
     * Pour ajouter un vol
     * @param vol
     * @return 
     */
    public DtoVol inserer(DtoVol vol);

    
    /**
     * Pour modifier une vol
     * @param vol
     * @return 
     */
    public DtoVol modifier(DtoVol vol);

    
    /**
     * Pour supprimer un vol
     * @param idVol 
     */
    public void supprimer(int idVol);

    
    /**
     * Pour obtenir une liste de tous les vols ayant la même ville de départ,
     * la même ville d'arrivé et la même date de départ que indiqués par les
     * paramètres
     * @param ville_dep
     * @param id_ville_arr
     * @param date_dep
     * @return 
     */
    public List<DtoVol> recherher(DtoVille ville_dep, DtoVille id_ville_arr, Date date_dep);

    
    /**
     * Pour obtenir la liste de tous les vols de la base de donnée
     * @return 
     */
    public List<DtoVol> listerTout();

}
