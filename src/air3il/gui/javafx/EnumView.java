package air3il.gui.javafx;

import javafx.scene.Parent;

public enum EnumView {

    // Valeurs
    Message("systeme/ViewMessage.fxml"),
    Connexion("systeme/ViewConnexion.fxml"),
    Vols("vols/ViewVols.fxml"),
    Reservation("reservation/ViewReservation.fxml"),
    Passager("passager/ViewInsererPassager.fxml"),
    Client("Client/ViewCleint.fxml"),
    Pays_Villes("pays/ViewAjoutPaysVille.fxml");
    

    // Champs
    private String path;
    private Parent parent;

    // Constructeur 
    EnumView(String path) {
        this.path = path;
    }

    // Propriétés
    public String getPathn() {
        return path;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
