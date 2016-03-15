package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoGroupe;

public interface IDaoGroupe {

    public void inserer(DtoGroupe groupe);

    public void modifier(DtoGroupe groupe);

    public void supprimer(int idGroupe);

    public DtoGroupe retrouver(int idGroupe);

    public List<DtoGroupe> listerTout();

    public List<DtoGroupe> listerPourCompte(int idCompte);

}
