package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditMoviesController {
	private static OnDemandMovie movie;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField engName;

    @FXML
    private TextField hebName;

    @FXML
    private TextField producer;

    @FXML
    private TextField actors;

    @FXML
    private AnchorPane signUpAnchor;

    @FXML
    private TextField newUserField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private CheckBox useEmailCheck;

    @FXML
    private Label warning;

    @FXML
    private TextArea description;

    @FXML
    private TextField price;

    @FXML
    private TextField thumbnail;

    @FXML
    private TextField streaming;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private ComboBox<?> hourField;

    @FXML
    private ComboBox<?> minuteField;

    @FXML
    private DatePicker datePick;

    @FXML
    void CancelAddition(ActionEvent event) {

    }

    @FXML
    void ConfirmAddition(ActionEvent event) {

    }

    @FXML
    void copyEmail(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	engName.setPromptText(movie.getMovieTitle());
    	hebName.setPromptText(movie.getMovieTitleHeb());
    	producer.setPromptText(movie.getMovieProducer());
    	actors.setPromptText(movie.getStarringActors());
    	description.setPromptText(movie.getMovieDescription());
    	price.setPromptText(String.valueOf(movie.getCost()));
    	thumbnail.setPromptText(movie.getImage().getImgURL());
    	streaming.setPromptText(movie.getStreamingLink());
    	ZonedDateTime date =   movie.getDateTimeStart();
		String formatted = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
		datePick.setPromptText(formatted);
		 formatted = DateTimeFormatter.ofPattern("hh").format(date);
		 hourField.setPromptText(formatted);
		 formatted = DateTimeFormatter.ofPattern("mm").format(date);
		 minuteField.setPromptText(formatted);
        assert engName != null : "fx:id=\"engName\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert hebName != null : "fx:id=\"hebName\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert producer != null : "fx:id=\"producer\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert actors != null : "fx:id=\"actors\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert signUpAnchor != null : "fx:id=\"signUpAnchor\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert newUserField != null : "fx:id=\"newUserField\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert newPasswordField != null : "fx:id=\"newPasswordField\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert useEmailCheck != null : "fx:id=\"useEmailCheck\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert warning != null : "fx:id=\"warning\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert thumbnail != null : "fx:id=\"thumbnail\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert streaming != null : "fx:id=\"streaming\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert hourField != null : "fx:id=\"hourField\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert minuteField != null : "fx:id=\"minuteField\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert datePick != null : "fx:id=\"datePick\" was not injected: check your FXML file 'editMovies.fxml'.";

    }

	public static OnDemandMovie getMovie() {
		return movie;
	}

	public static void setMovie(OnDemandMovie movie) {
		EditMoviesController.movie = movie;
	}
}
