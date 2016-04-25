/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air3il.commun.service;

import air3il.commun.dto.DtoReservation;
import air3il.commun.exception.ExceptionAppli;
import java.util.List;

/**
 *
 * @author ARSENE
 */
public interface IServiceReservation {
    
   	public DtoReservation inserer( DtoReservation dto ) throws ExceptionAppli;
	
	public List<DtoReservation> listerTout() throws ExceptionAppli;
}
