package air3il.commun.dao;

import java.util.List;
import java.util.Date;

import air3il.commun.dto.DtoVol;
import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoPays;

public interface IDaoVol {

    public void inserer(DtoVol vol);

    public void modifier(DtoVol vol);

    public void supprimer(int idVol);

    public List<DtoVol> recherher(DtoVille ville_dep, DtoVille id_ville_arr, Date date_dep);

    public List<DtoVol> listerTout();

}
