package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddMoviePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField engMovieTitle;

    @FXML
    private TextField hebMovieTitle;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField producersField;

    @FXML
    private TextField costField;

    @FXML
    private TextField actorsField;

    @FXML
    private TextField urlField;

    @FXML
    private ImageView movieImage;

    @FXML
    void initialize() {
        assert engMovieTitle != null : "fx:id=\"engMovieTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert hebMovieTitle != null : "fx:id=\"hebMovieTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert descriptionField != null : "fx:id=\"descriptionField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert producersField != null : "fx:id=\"producersField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert costField != null : "fx:id=\"costField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert actorsField != null : "fx:id=\"actorsField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert urlField != null : "fx:id=\"urlField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert movieImage != null : "fx:id=\"movieImage\" was not injected: check your FXML file 'addMoviePage.fxml'.";

    }
}
