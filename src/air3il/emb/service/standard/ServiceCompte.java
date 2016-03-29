/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.emb.service.standard;

import air3il.commun.dao.IDaoCompte;
import air3il.commun.dto.DtoCompte;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionAutorisation;
import air3il.commun.service.IServiceCompte;
import java.util.List;

/**
 *
 * @author projet
 */
public class ServiceCompte implements IServiceCompte {

    // Champs 
    private ManagerService managerService;
    private IDaoCompte daoCompte;

    // Constructeur
    public ServiceCompte(ManagerService managerService) {
        this.managerService = managerService;
        this.daoCompte = managerService.getManagerDao().getDao(IDaoCompte.class);
    }

    @Override
    public DtoCompte inserer(DtoCompte dto) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
        } catch (ExceptionAutorisation e) {
            throw new ExceptionAppli(e);
        }
        daoCompte.inserer(dto);
        /*
         *  TODO : revoyer l'id générer
        */
        return null;
    }

    @Override
    public DtoCompte modifier(DtoCompte dto) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int idCompte) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoCompte retrouver(int idCompte) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DtoCompte> listerTout() throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoCompte validerAuthentification(String pseudo, String motDePasse) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
