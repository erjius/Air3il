package air3il.gui.javafx.obs;

import air3il.commun.dto.DtoPassager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObsPassager {
	

	// Données observables
	
	private final StringProperty	propId	 = new SimpleStringProperty();
	private final StringProperty	propNom	 = new SimpleStringProperty();
	private final StringProperty	propPrenom = new SimpleStringProperty();
	private final StringProperty	propTel	 = new SimpleStringProperty();
	private final StringProperty	propEmail = new SimpleStringProperty();

 
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
   public StringProperty getPropTel() {
        return propTel;
    }

    public StringProperty getPropEmail() {
        return propEmail;
    }
	
	
	// Constructeurs
	
	public ObsPassager() {

	}
	
	public ObsPassager( String id, String nom, String prenom ,String tel, String email) {
		propId.set( id );
		propNom.set( nom );
		propPrenom.set( prenom );
                propTel.set( tel );
		propEmail.set( email );
	}
	
	public ObsPassager( ObsPassager passager ) {
        copierDonnees( passager );
	}
	
	public ObsPassager( DtoPassager dto ) {
        copierDonnees( dto );
	}
	
	
	// toString()
	@Override
	public String toString() {
		return  propNom.get() + " "  + propPrenom.get() + " " + propTel.get() + " "  + propEmail.get();
	}

	
	// Actions
	
	public void copierDonnees( ObsPassager p ) {
            
		propId.set( p.getPropId().get() );
		propNom.set( p.getPropNom().get() );
		propPrenom.set( p.getPropPrenom().get() );
                propTel.set( p.getPropTel().get() );
                propEmail.set( p.getPropEmail().get() );
                System.out.println("copierDonnees obs");
	}
	
	public void copierDonnees( DtoPassager dto ) {
            
		propId.set( String.valueOf( dto.getId() ) );
		propNom.set( dto.getNom() );
		propPrenom.set( dto.getPrenom() );
		propTel.set( dto.getTel());
		propEmail.set( dto.getEmail());
                
                  System.out.println("copierDonnees dto");
	}

	
	public DtoPassager crerDto() {
            
		DtoPassager dtoPassager = new DtoPassager();
		if( propId.get() != null && ! propId.get().isEmpty() ) {
			dtoPassager.setId( Integer.parseInt( propId.get() ));
		}
		dtoPassager.setNom( propNom.get());
		dtoPassager.setPrenom( propPrenom.get() );
		dtoPassager.setTel(propTel.get());
		dtoPassager.setEmail(propEmail.get() ); 
                System.out.println("crerDto");
                
		return dtoPassager;
	}
	
}
