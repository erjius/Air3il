package air3il.commun.service;

import air3il.commun.dto.DtoVille;
import air3il.commun.dto.DtoVol;
import java.util.List;
import air3il.commun.exception.ExceptionAppli;
import java.util.Date;
import java.util.Map;
import java.util.TreeSet;

public interface IServiceVols {
    
   
    public List<DtoVol> listervols(DtoVille depart, DtoVille arrive,Date date)throws ExceptionAppli;
    
    public List<DtoVol> lstToutVol()throws ExceptionAppli;
    
    public void ajoutVols(DtoVol vols) throws ExceptionAppli;
    
    public void modifierVols(DtoVol vols)throws ExceptionAppli;
    
    public void annulerVols(DtoVol vols)throws ExceptionAppli;
    
    
    
    

}
