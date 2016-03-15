package air3il.gui.javafx;

import air3il.gui.javafx.systeme.ModelSysteme;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import air3il.commun.exception.ExceptionAppli;
import air3il.commun.exception.ExceptionAutorisation;
import air3il.commun.service.IManagerService;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ManagerGui {

    // Variables statiques
    private static final Logger logger = Logger.getLogger(ManagerGui.class.getName());

    // Champs
    private final IManagerService managerService;
    private final Stage stagePrincipal;
    private final Map<Class, Object> mapModels = new HashMap<>();

    private BorderPane panePrincipal;

    // Constructeur
    public ManagerGui(Stage stage, IManagerService managerService) throws IOException {
        this.stagePrincipal = stage;
        this.managerService = managerService;
        logger.log(Level.CONFIG, "ManagerService : {0}", managerService.getClass().getName());
    }

    // Actions d'initialisation
    public void show() throws Exception {

        // Instancie le panneau et le controleur
        String path = "systeme/PanePrincipal.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        panePrincipal = loader.load();

        // Injecte les dépandances
        IControllerJavaFx controller = loader.getController();
        controller.setManagerGui(this);

        // Prépare l'afficage
        Scene scene = new Scene(panePrincipal);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stagePrincipal.setScene(scene);
        stagePrincipal.setMinHeight(300);
        stagePrincipal.setMinWidth(400);
        stagePrincipal.sizeToScene();
        stagePrincipal.setTitle("Air3il");
        stagePrincipal.getIcons().add(new Image(getClass().getResource("icone.png").toExternalForm()));
        showView(EnumView.Connexion);

        // Libère les ressources à la fermeture de l'application
        stagePrincipal.setOnHiding((event) -> {
            managerService.close();
        });

        // Affiche la fenêtre principale
        stagePrincipal.show();
    }

    // Propriétés
    public IManagerService getManagerService() {
        return managerService;
    }

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    @SuppressWarnings("unchecked")
    public <T> T getModel(Class<T> type) throws Exception {

        // Recherche dans la map
        Object model = mapModels.get(type);

        // Si pas trouvé dans la map
        if (model == null) {
            // Détermine le type à instancier
            Constructor<T> constructor = type.getConstructor(new Class[]{ManagerGui.class});

            // Instancie un objet et lui injecte les dépendances
            model = constructor.newInstance(new Object[]{this});

            // Ajoute l'objet à la map
            mapModels.put(type, model);
        }

        return (T) model;
    }

    // Affichage des vues
    public void showView(EnumView view) {

        try {

            Parent parent = view.getParent();

            if (parent == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(view.getPathn()));
                parent = loader.load();

                // Injecte les dépandances
                IControllerJavaFx controller = loader.getController();
                controller.setManagerGui(this);

                // Ajoute l'objet à la map
                view.setParent(parent);
            }
            // Affiche la vue
            panePrincipal.setCenter(parent);
        } catch (Exception e) {
            afficherErreur("Problème lors de l'affichage", e);
        }
    }

    // Actions générales
    public void afficherMessage(String message) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stagePrincipal);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void afficherErreur(Exception exception) {
        afficherErreur(null, exception);
    }

    public void afficherErreur(String message) {
        afficherErreur(message, null);
    }

    public void afficherErreur2(String message, Exception exception) {

        String messageDefaut = "Ecec du traitement demandé.";

        if (exception != null) {

            // Trace de l'excepton et message par défaut
            if (exception.getClass().getName().equals("javax.ejb.EJBException")
                    || exception.getClass().getName().equals("javax.ejb.EJBTransactionRolledbackException")) {
                messageDefaut = "EJB : Echec du traitement demandé";
                logger.log(Level.FINE, exception.getMessage(), exception);

            } else if (exception.getClass().getName().equals("javax.ejb.EJBAccessException")) {
                messageDefaut = "EJB : Action non autoriésé !";
                logger.log(Level.FINEST, exception.getMessage(), exception);

            } else if (exception instanceof ExceptionAutorisation) {
                messageDefaut = "Action non autoriésé !";
                logger.log(Level.FINEST, exception.getMessage(), exception);

            } else if (!(exception instanceof ExceptionAppli)) {
                logger.log(Level.SEVERE, exception.getMessage(), exception);
            }

            // Message transporté par une ExceptionAppli sans cause
            if ((exception instanceof ExceptionAppli
                    || exception.getClass().getName().equals("java.lang.Exception"))
                    && exception.getCause() == null
                    && exception.getMessage() != null) {
                messageDefaut = exception.getMessage();
            }

        }

        if (message == null) {
            message = messageDefaut;
        }

        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stagePrincipal);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void afficherErreur(String message, Exception exception) {
        String messageDefaut = null;
        if (exception != null) {

            if (exception instanceof ExceptionAppli && message == null) {
                message = exception.getMessage();
            }

            if (exception.getClass().getName().equals("javax.ejb.EJBException")
                    || exception.getClass().getName().equals("javax.ejb.EJBTransactionRolledbackException")) {
                messageDefaut = "EJB : Echec du traitement demandé";
                logger.log(Level.FINE, exception.getMessage(), exception);
            } else if (exception.getClass().getName().equals("javax.ejb.EJBAccessException")) {
                messageDefaut = "EJB : Action non autoriésé !";
                logger.log(Level.FINEST, exception.getMessage(), exception);
            } else if (exception instanceof ExceptionAutorisation) {
                messageDefaut = "Action non autoriésé !";
                logger.log(Level.FINEST, exception.getMessage(), exception);
            } else if (!(exception instanceof ExceptionAppli)) {
                logger.log(Level.SEVERE, exception.getMessage(), exception);
            }
            if (message == null) {
                if (messageDefaut != null) {
                    message = messageDefaut;
                } else if (exception.getClass() == ExceptionAppli.class
                        && (exception.getCause() != null)
                        || exception.getMessage() == null) {
                    message = "Echec du traitement demandé.";
                } else {
                    message = exception.getMessage();
                }
            }
            if (message == null) {
                message = exception.getClass().getName();
            }
        }
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stagePrincipal);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public boolean demanderConfirmation(String message) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stagePrincipal);
        alert.setHeaderText(message);
        final Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    // Réinitialisation des données (fermeture session utilisateur)
    @SuppressWarnings("rawtypes")
    public void reinitGui() {

        // Supprime les vues (sauf la vue connexion)
        for (EnumView vue : EnumView.values()) {
            if (vue != EnumView.Connexion) {
                vue.setParent(null);
            }
        }

        // Supprime les modèles sauf le modèle système
        for (Class classe : new ArrayList<>(mapModels.keySet())) {
            if (classe != (Class) ModelSysteme.class) {
                mapModels.remove(classe);
            }
        }

    }

}
