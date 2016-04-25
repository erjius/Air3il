package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoPays;

public interface IDaoPays {
    
    
    /**
     * Pour ajouter un nouveau pays
     * @param pays
     * @return 
     */
    public DtoPays inserer(DtoPays pays);

    
    /**
     * Pour modifier un pays
     * @param pays
     * @return 
     */
    public DtoPays modifier(DtoPays pays);

    
    /**
     * Pour supprimer un pays
     * @param idPays 
     */
    public void supprimer(int idPays);

    /**
     * Pour trouver un pays à partir de son id
     * @param idPays
     * @return 
     */
    public DtoPays retrouver(int idPays);
    
    
    /**
     * Pour obtenir la liste des pays de la base de donnée
     * @return 
     */
    public List<DtoPays> listerTout();
}
