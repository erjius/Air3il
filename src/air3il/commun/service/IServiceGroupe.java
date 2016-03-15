package air3il.commun.service;

import java.util.List;

import air3il.commun.dto.DtoGroupe;
import air3il.commun.exception.ExceptionAppli;

public interface IServiceGroupe {

    public DtoGroupe inserer(DtoGroupe dto) throws ExceptionAppli;

    public DtoGroupe modifier(DtoGroupe dto) throws ExceptionAppli;

    public void supprimer(int idGroupe) throws ExceptionAppli;

    public DtoGroupe retrouver(int idGroupe) throws ExceptionAppli;

    public List<DtoGroupe> listerTout() throws ExceptionAppli;

}
