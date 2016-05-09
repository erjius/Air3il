package air3il.emb.service.test;

import air3il.commun.dto.DtoPassager;
import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionValidation;
import air3il.commun.service.IServicePassager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServicePassager implements IServicePassager {


	// Logger
	private static final Logger logger = Logger.getLogger( ServicePassager.class.getName() );

	
	// Champs 
	
    private final ManagerService            managerService;
    private final Map<Integer, DtoPassager>	mapPassagers;
	

	// Constructeur

    public ServicePassager( ManagerService managerService ) {
        this.managerService = managerService;
		mapPassagers = managerService.getMapPassagers();
	}
	

	// Actions 

	@Override
	public DtoPassager inserer(DtoPassager passager) throws ExceptionAppli {
		try {
			managerService.verifierAutorisationUtilisateur();
			verifierValiditeDonnees( passager );
			if ( mapPassagers.isEmpty() ) {
				passager.setId(1 );
			} else {
				passager.setId( Collections.max( mapPassagers.keySet() ) + 1 );
			}
			
			mapPassagers.put( passager.getId(), passager );
			return passager;
		} catch (RuntimeException e) {
			logger.log( Level.SEVERE, e.getMessage(), e );
			throw new ExceptionAppli(e);
		}
	}

	@Override
	public DtoPassager modifier(DtoPassager passager) throws ExceptionAppli {
		try {
			managerService.verifierAutorisationUtilisateur();
			verifierValiditeDonnees( passager );
			
			mapPassagers.replace( passager.getId(), passager );
			return passager;
		} catch (RuntimeException e) {
			logger.log( Level.SEVERE, e.getMessage(), e );
			throw new ExceptionAppli(e);
		}
	}

	@Override
	public void supprimer(int idPassager) throws ExceptionAppli  {
		try {
			managerService.verifierAutorisationUtilisateur();
			mapPassagers.remove( idPassager );
		} catch (RuntimeException e) {
			logger.log( Level.SEVERE, e.getMessage(), e );
			throw new ExceptionAppli(e);
		}
	}

	@Override
	public DtoPassager retrouver(int idPassager) throws ExceptionAppli {
		try {
			managerService.verifierAutorisationUtilisateur();
			DtoPassager passager = mapPassagers.get( idPassager );
			return passager;
		} catch (RuntimeException e) {
			logger.log( Level.SEVERE, e.getMessage(), e );
			throw new ExceptionAppli(e);
		}
	}

	@Override
	public List<DtoPassager> listerTout() throws ExceptionAppli {
		try {
			managerService.verifierAutorisationUtilisateur();
			return new ArrayList<>(mapPassagers.values());
		} catch (RuntimeException e) {
			logger.log( Level.SEVERE, e.getMessage(), e );
			throw new ExceptionAppli(e);
		}
	}

	
	
	// Méthodes auxiliaires
	

	private void verifierValiditeDonnees( DtoPassager passager ) throws ExceptionAppli {
		
		StringBuilder message = new StringBuilder();
		
		if ( passager.getNom() == null || passager.getNom().isEmpty() ) {
			message.append( "\nLe nom est absent." );
		} else 	if ( passager.getNom().length() > 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if ( passager.getPrenom() == null || passager.getPrenom().isEmpty() ) {
			message.append( "\nLe prénom est absent." );
		} else 	if ( passager.getPrenom().length() > 25 ) {
			message.append( "\nLe prénom est trop long." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
	}
	
}
