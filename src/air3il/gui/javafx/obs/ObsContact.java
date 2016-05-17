package air3il.gui.javafx.obs;

import air3il.commun.dto.DtoContact;
import air3il.commun.dto.DtoReservation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObsContact {
	

	// Données observables
	
	private final StringProperty	propId	 = new SimpleStringProperty();
	private final StringProperty	propTel	 = new SimpleStringProperty();
	private final StringProperty	propEmail = new SimpleStringProperty();
	
	
	// Accès aux données observables
	
	public StringProperty getPropId() {
		return propId;
	}
	public StringProperty getPropTel() {
		return propTel;
	}
	public StringProperty getPropEmail() {
		return propEmail;
	}
	
	
	// Constructeurs
	
	public ObsContact() {

	}
	
	public ObsContact( String id, String tel, String email ) {
		propId.set( id );
		propTel.set( tel );
		propEmail.set( email );
	}

	public ObsContact( ObsContact contact ) {
		this.copierDonnees(contact);
	}
	
	public ObsContact( DtoContact dto ) {
		propId.set( String.valueOf( dto.getId() ));
		propTel.set( dto.getTel());
		propEmail.set( dto.getEmail() );
	}
	
	
	// toString()
	@Override
	public String toString() {
		return  propTel.get() + " "  + propEmail.get();
	}

	
	// Actions
	
	public void copierDonnees( ObsContact t ) {
		propId.set( t.getPropId().get() );
		propTel.set( t.getPropTel().get() );
		propEmail.set( t.getPropEmail().get() );
	}
	
	public DtoContact creerDto( DtoReservation reservationCopiee ) {
		DtoContact dto = new DtoContact();
		if( propId.get() != null && ! propId.get().isEmpty() ) {
			dto.setId( Integer.parseInt( propId.get() ));
		}
        dto.setReservation( reservationCopiee );
		dto.setTel( propTel.get() );
		dto.setEmail( propEmail.get() );
		return dto;
	}

	

}
