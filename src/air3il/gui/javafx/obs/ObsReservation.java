
package air3il.gui.javafx.obs;

import air3il.commun.dto.DtoContact;
import air3il.commun.dto.DtoReservation;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ARSENE
 */
public class ObsReservation {
    
	// Données observables
	
	private final StringProperty	propId	 = new SimpleStringProperty();
	private final StringProperty	propNom	 = new SimpleStringProperty();
	private final StringProperty	propPrenom = new SimpleStringProperty();
        private final StringProperty	propPlace	 = new SimpleStringProperty();
	private final StringProperty	propVol = new SimpleStringProperty();
        private final ObservableList<ObsContact>  obsListContacts = FXCollections.observableArrayList(
			t ->  new Observable[] { t.getPropTel(), t.getPropEmail() } 
		);


        
        	
	// Accès aux données observables
	
	public StringProperty getPropId() {
		return propId;
	}
	public StringProperty getPropNom() {
		return propNom;
	}
	public StringProperty getPropPrenom() {
		return propPrenom;
	}
        public StringProperty getPropPlace() {
		return propPlace;
	}
        
        public StringProperty getPropVol() {
		return propVol;
	}
        
        
	public ObservableList<ObsContact> getObsListContacts() {
		return obsListContacts;
	}
	
	
	// Constructeurs
	
	public ObsReservation() {

	}
	
	public ObsReservation( String id, String nom, String prenom,String place,String Vol ) {
		propId.set( id );
		propNom.set( nom );
		propPrenom.set( prenom );
                propPlace.set(place);
                propVol.set(Vol);
	}
	
	public ObsReservation( ObsReservation reservation ) {
        copierDonnees( reservation );
	}
	
	public ObsReservation( DtoReservation dto ) {
        copierDonnees( dto );
	}
	
	
	// toString()
	@Override
	public String toString() {
		return  propNom.get() + " "  + propPrenom.get();
	}

	
	// Actions
	
	public void copierDonnees( ObsReservation p ) {
		propId.set( p.getPropId().get() );
		propNom.set( p.getPropNom().get() );
		propPrenom.set( p.getPropPrenom().get() );
                propPlace.set( p.getPropPlace().get() );
		propVol.set( p.getPropVol().get() );
		obsListContacts.clear();
		for( ObsContact t : p.getObsListContacts() ) {
			obsListContacts.add( new ObsContact(t) ) ;
		}
	}
	
	public void copierDonnees( DtoReservation dto ) {
		propId.set( String.valueOf( dto.getId() ) );
		propNom.set( dto.getNom_pass());
		propPrenom.set( dto.getPrenom_pass());
		obsListContacts.clear();
		for( DtoContact t : dto.getContacts() ) {
			obsListContacts.add( new ObsContact(t) );
		}
	}

	
	public DtoReservation crerDto() {
		DtoReservation dtoReservation = new DtoReservation();
		if( propId.get() != null && ! propId.get().isEmpty() ) {
			dtoReservation.setId( Integer.parseInt( propId.get() ));
		}
		dtoReservation.setNom_pass(propNom.get());
		dtoReservation.setPrenom_pass(propPrenom.get() );
		for ( ObsContact t1 : obsListContacts) {
			DtoContact t2 = t1.creerDto(dtoReservation );
			dtoReservation.ajouterContact(t2);
		}
		return dtoReservation;
	}
	
    
}
