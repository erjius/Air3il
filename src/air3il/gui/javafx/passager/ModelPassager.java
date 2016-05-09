package air3il.gui.javafx.passager;

import air3il.commun.dto.DtoPassager;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServicePassager;
import air3il.gui.javafx.EnumModeVue;
import static air3il.gui.javafx.EnumModeVue.CREER;
import static air3il.gui.javafx.EnumModeVue.MODIFIER;
import air3il.gui.javafx.ManagerGui;
import air3il.gui.javafx.obs.ObsPassager;
import java.util.Comparator;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ARSENE
 */
public class ModelPassager {
	
	
	// Données observables 
	
	private final ObservableList<ObsPassager> obsListPassagers = FXCollections.observableArrayList( 
			p ->  new Observable[] { p.getPropNom(), p.getPropPrenom(),p.getPropTel(), p.getPropEmail() } 
		);
	
	private final ObsPassager	passagerVue = new ObsPassager( "99", "", "", "", "" );
	
	
	// Objet courant
	
	private ObsPassager     	passagerCourante;
         private EnumModeVue         modeVue;
	
	
	// Autres champs
	
	private final IServicePassager	servicePassager;
	
	
	// Propriétés
	
	public ObservableList<ObsPassager> getObsListPassagers() {
		return obsListPassagers;
	}
	
	public ObsPassager getPassagerVue() {
		return passagerVue;
	}

	
	// Constructeur
    
	public ModelPassager( ManagerGui managerGui ) throws Exception {
        servicePassager = managerGui.getManagerService().getService( IServicePassager.class );
        actualiserListe();
    }

	
	// Actualisations
	
	public void actualiserListe() throws Exception  {

		// Prépare l'initialisaiton de l'objet courant
		String idCourant = null;
		if( passagerCourante != null ) {
			idCourant = passagerCourante.getPropId().get();
		}
		
		// Actualise la liste
		obsListPassagers.clear();
		for( DtoPassager dto : servicePassager.listerTout() ) {
            ObsPassager passager = new ObsPassager(dto);
			if( passager.getPropId().get().equals( idCourant )) {
				passagerCourante = passager;
			}
			obsListPassagers.add( passager );
		}
        trierListe();
	}

	
	// Actions
	
	public void preparerAjouter() {
        modeVue = CREER;
		passagerCourante = null;
		passagerVue.copierDonnees( new ObsPassager() );
	}
	
	public void preparerModifier( ObsPassager passager ) {
        modeVue = MODIFIER;
		passagerCourante = passager;
		passagerVue.copierDonnees( passager );
	}
	
	public void ValiderMiseAJour() throws Exception {
		
		String nom = passagerVue.getPropNom().get();
		String prenom = passagerVue.getPropPrenom().get();
		
		StringBuilder message = new StringBuilder();
		if( nom == null || nom.isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( nom.length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}
		if( prenom == null || prenom.isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( prenom.length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		// Test si c'est un ajout ou une modificaiton
                modeVue = CREER;
        if ( modeVue == CREER ) {
			DtoPassager dto = servicePassager.inserer( passagerVue.crerDto() );
            passagerCourante = new ObsPassager(dto);
			obsListPassagers.add(passagerCourante);
		}
        if ( modeVue == MODIFIER ) {
			DtoPassager dto = servicePassager.modifier( passagerVue.crerDto() );
			passagerCourante.copierDonnees( dto );
		}

        // Trie la liste
        trierListe();
	}
	
	public void supprimer( ObsPassager passager ) throws Exception {
		int id = Integer.parseInt( passager.getPropId().get() );
		servicePassager.supprimer(id);
		obsListPassagers.remove(passager);
	}
	
	
    
    
    // Méthodes auxiliaires
    
    private void trierListe() {
		FXCollections.sort( obsListPassagers,
            (Comparator<ObsPassager>) ( p1, p2) -> {
                int lastCmp = p1.getPropNom().get().toUpperCase().compareTo(p2.getPropNom().get().toUpperCase());
                return (lastCmp != 0 ? lastCmp : p1.getPropPrenom().get().toUpperCase().compareTo(p2.getPropPrenom().get()));
		});
    }
	
}