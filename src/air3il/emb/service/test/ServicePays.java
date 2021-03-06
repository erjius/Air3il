/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.emb.service.test;

import air3il.commun.dto.DtoPays;
import air3il.commun.dto.DtoVille;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServicePays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author projet
 */
public class ServicePays implements IServicePays {

    // Logger
    private static final Logger logger = Logger.getLogger(ServiceCompte.class.getName());

    // Champs 
    private final ManagerService managerService;

    private final Map<Integer, DtoPays> mapPays;

    public ServicePays(ManagerService m) {
        managerService = m;
        mapPays = m.getMapPays();
    }

    @Override
    public DtoPays insererPays(DtoPays dto) throws ExceptionAppli, ExceptionValidation {
        try {
            managerService.verifierAutorisationAdmin();
            dto.setId(0);
            verifierValiditeDonnees(dto);
            if (mapPays.isEmpty()) {
                dto.setId(1);
            } else {
                dto.setId(Collections.max(mapPays.keySet()) + 1);
            }
            mapPays.put(dto.getId(), dto);
            return dto;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoPays modifierPays(DtoPays dto) throws ExceptionAppli, ExceptionValidation {
        try {
            managerService.verifierAutorisationAdmin();
            verifierValiditeDonnees(dto);
            mapPays.replace(dto.getId(), dto);
            return dto;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }
    
    @Override
    public List<DtoVille> listerVilleParPays(DtoPays pays) throws ExceptionAppli
    {
       List<DtoVille> listevilles = new ArrayList<>();
       for(DtoVille v : listerToutVille()){
           if(v.getPays().equals(pays)){
               listevilles.add(v);
           }
       }
       return listevilles;
    }
    
    @Override
    public void supprimerPays(int idPays) throws ExceptionAppli {
        try {
            managerService.verifierAutorisationAdmin();
            mapPays.remove(idPays);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoPays retrouverPays(int idPays) throws ExceptionAppli {
        return mapPays.get(idPays);
    }

    @Override
    public List<DtoPays> listerToutPays() throws ExceptionAppli {
        return new ArrayList<>(mapPays.values());
    }

    @Override
    public DtoPays chercherPays(String nom) throws ExceptionAppli {
        for (DtoPays p : mapPays.values()) {
            if (p.getNom().equalsIgnoreCase(nom)) {
                return p;
            }
        }
        return null;
    }

    private void verifierValiditeDonnees(DtoPays dto) throws ExceptionAppli, ExceptionValidation {
        StringBuilder message = new StringBuilder();
        if (dto.getNom() == null) {
            throw new ExceptionValidation("Le pays doit avoir un nom");
        }
        if (dto.getNom().length() < 3) {
            message.append("Le pays doit posséder un nom de plus de 3 caractères\n");
        } else if (dto.getNom().length() > 25) {
            message.append("Le nom du pays doit faire moins de 20 caractères\n");
        }
        if (chercherPays(dto.getNom()) != null) {
            message.append("Le pays existe déjà\n");
        }

        if (message.length() != 0) {
            throw new ExceptionValidation(message.toString());
        }
    }

    private void verifierValiditeDonnees(DtoVille dto) throws ExceptionAppli, ExceptionValidation {
        StringBuilder message = new StringBuilder();
        if (dto.getNom() == null) {
            throw new ExceptionValidation("Le pays doit avoir un nom");
        }
        if (dto.getNom().length() < 3) {
            message.append("Le pays doit posséder un nom de plus de 3 caractères\n");
        } else if (dto.getNom().length() > 25) {
            message.append("Le nom du pays doit faire moins de 20 caractères\n");
        }
        if (chercherPays(dto.getNom()) != null) {
            message.append("Le pays existe déjà\n");
        }

        if (message.length() != 0) {
            throw new ExceptionValidation(message.toString());
        }
    }

    @Override
    public DtoVille insererVille(DtoVille dto) throws ExceptionAppli, ExceptionValidation {
        try {
            managerService.verifierAutorisationAdmin();
            dto.setId(0);
            verifierValiditeDonnees(dto);
            if (managerService.getListVille().isEmpty()) {
                dto.setId(1);
            } else {
                dto.setId(Collections.max(managerService.getListVille(),
                        (DtoVille o1, DtoVille o2) -> Integer.compare(o1.getId(), o2.getId())).getId()+1);
            }
            managerService.getListVille().add(dto);
            return dto;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ExceptionAppli(e);
        }
    }

    @Override
    public DtoVille modifierVille(DtoVille dto) throws ExceptionAppli, ExceptionValidation {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerVille(int id) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoVille retrouverVille(int id) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoVille chercherVille(String nom) throws ExceptionAppli {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DtoVille> listerToutVille() throws ExceptionAppli {
        return managerService.getListVille();
    }

}
