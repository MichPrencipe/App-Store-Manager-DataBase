package launcher;

/**
 * This class create the string path to upload the scene of the game
 * 
 * @author PRENCIPE MICHELE
 *
 */
public enum FXMLPath {
    /**
     * Fields to manage panels of the game.
     */

    LAUNCHER("mainView"), LOGIN_AUTORE("login_autore"), AUTHOR("author"), USER("user"), LOGIN_UTENTE("login_utente");

    /**
     * This fields save the generic path of the scene.
     */
    private final String name;
    private final String path = "/fxml/files/";
    private final String extension = ".fxml";

    /**
     * Constructor of the class, save the name of the scene.
     * 
     * @param nameScene of the scene to upload.
     */
    FXMLPath(final String nameScene) {
        this.name = nameScene;
    }

    /**
     * Create the true path of the scene.
     * 
     * @return path of the scene to upload.
     */
    public String getPath() {
        return this.path + this.name + this.extension;
    }
}
