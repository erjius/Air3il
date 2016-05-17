package air3il.gui.javafx;

import javafx.scene.Parent;

public enum EnumView {

    // Valeurs
    Message("systeme/ViewMessage.fxml"),
    Connexion("systeme/ViewConnexion.fxml"),
    Vols("vols/ViewVols.fxml"),
    Contact("reservation/ViewContactForm.fxml"),
    Reservation("reservation/ViewReservationListe.fxml"),
    Client("client/ViewClient.fxml"),
    Pays_Villes("pays/ViewAjoutPaysVille.fxml"),
    Avion("place/FXMLavion.fxml");

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
