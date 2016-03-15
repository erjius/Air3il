package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoCompte;

public interface IDaoCompte {

    public void inserer(DtoCompte compte);

    public void modifier(DtoCompte compte);

    public void supprimer(int idCompte);

    public DtoCompte retrouver(int idCompte);

    public List<DtoCompte> listerTout();

    public DtoCompte validerAuthentification(String pseudo, String motDePasse);

    public boolean verifierUnicitePseudo(String pseudo, int idCompte);

}
