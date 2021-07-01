package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
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
	private static Movie movie;
	public static int typeIndex; // 1 - Cinema Movie || 2 - On-Demand || 3 - Coming soon


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
    private ComboBox<Integer> hourField;

    @FXML
    private ComboBox<Integer> minuteField;

    @FXML
    private DatePicker datePick;
    
    @FXML
    private Button changePriceOrLinkBtn;
    
    @FXML
    private AnchorPane buttonAnchor;

    @FXML
    void CancelAddition(ActionEvent event) {
    	try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ConfirmAddition(ActionEvent event) {
    	if(engName.getText() != "") movie.setMovieTitle(engName.getText());
    	if(hebName.getText() != "") movie.setMovieTitleHeb(hebName.getText());
    	if(producer.getText() != "") movie.setMovieProducer(producer.getText());
    	if(actors.getText() != "") movie.setStarringActors(actors.getText());
    	if(description.getText() != "") movie.setMovieDescription(description.getText());
    	if(thumbnail.getText() != "") movie.getImage().setImgURL(thumbnail.getText());
    	try {
			SimpleClient.getClient().sendToServer(new Message("#EditMovieAbstract", movie));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void copyEmail(ActionEvent event) {

    }
    
    @FXML
    void ChangePriceOrLink(ActionEvent event) {
    	EditStreemingLinkAndDateController.setMovie((OnDemandMovie)movie);
    	
    	try {
			App.setRoot("editStreemingLinkAndDate");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
    	engName.setPromptText(movie.getMovieTitle());
    	hebName.setPromptText(movie.getMovieTitleHeb());
    	producer.setPromptText(movie.getMovieProducer());
    	actors.setPromptText(movie.getStarringActors());
    	description.setPromptText(movie.getMovieDescription());
    	thumbnail.setPromptText(movie.getImage().getImgURL());
    	buttonAnchor.setVisible(false);
    	
    	if(typeIndex == 2) {
    		buttonAnchor.setVisible(true);
    	}
    	else {
    		buttonAnchor.setVisible(false);

    	}

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
        assert changePriceOrLinkBtn != null : "fx:id=\"changePriceOrLinkBtn\" was not injected: check your FXML file 'editMovies.fxml'.";
        assert buttonAnchor != null : "fx:id=\"buttonAnchor\" was not injected: check your FXML file 'editMovies.fxml'.";

    }

	public static Movie getMovie() {
		return movie;
	}

	public static void setMovie(Movie movie) {
		EditMoviesController.movie = movie;
	}

	public int getTypeIndex() {
		return typeIndex;
	}

	public static void setTypeIndex(int typeIndex) {
		EditMoviesController.typeIndex = typeIndex;
	}
}
