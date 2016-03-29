package air3il.commun.dao;

import air3il.commun.dto.DtoPays;
import java.util.List;

import air3il.commun.dto.DtoVille;

public interface IDaoVille {

    public DtoVille inserer(DtoVille ville);

    public DtoVille modifier(DtoVille ville);

    public void supprimer(int idVille);

    public DtoVille retrouver(int idVille);

    public List<DtoVille> listerTout();
    
    public List<DtoVille> listerVilleParPays (DtoPays pays);
       
}
