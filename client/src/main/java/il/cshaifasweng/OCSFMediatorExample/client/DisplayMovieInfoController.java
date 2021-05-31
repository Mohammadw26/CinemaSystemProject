package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DisplayMovieInfoController {
	
	private Movie movie;
	private int counter;
	private String temp = "Screening times:\n";
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="imageField"
    private ImageView imageField; // Value injected by FXMLLoader

    @FXML // fx:id="titleField"
    private Label titleField; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionField"
    private Label descriptionField; // Value injected by FXMLLoader

    @FXML // fx:id="ProducersField"
    private Label ProducersField; // Value injected by FXMLLoader

    @FXML // fx:id="starsField"
    private Label starsField; // Value injected by FXMLLoader

    @FXML // fx:id="costField"
    private Label costField; // Value injected by FXMLLoader

    @FXML // fx:id="screeningField"
    private Label screeningField; // Value injected by FXMLLoader
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert imageField != null : "fx:id=\"imageField\" was not injected: check your FXML file 'displayMovieInfo.fxml'.";
        assert titleField != null : "fx:id=\"titleField\" was not injected: check your FXML file 'displayMovieInfo.fxml'.";
        assert descriptionField != null : "fx:id=\"descriptionField\" was not injected: check your FXML file 'displayMovieInfo.fxml'.";
        assert ProducersField != null : "fx:id=\"ProducersField\" was not injected: check your FXML file 'displayMovieInfo.fxml'.";
        assert starsField != null : "fx:id=\"starsField\" was not injected: check your FXML file 'displayMovieInfo.fxml'.";
        assert costField != null : "fx:id=\"costField\" was not injected: check your FXML file 'displayMovieInfo.fxml'.";
        assert screeningField != null : "fx:id=\"screeningField\" was not injected: check your FXML file 'displayMovieInfo.fxml'.";
    }
	
	public void setDisplay () {
		titleField.setText(movie.getMovieTitle());
		descriptionField.setText(movie.getMovieDescription());
		ProducersField.setText(movie.getMovieProducer());
		starsField.setText(movie.getStarringActors());
		costField.setText(movie.getTicketCost() + "$");
		counter = movie.getScreenings().size();
		for (int i = 0 ; i < counter; i++) {
			temp += movie.getScreenings().get(i).getScreeningDate() + " ";
			temp += movie.getScreenings().get(i).getScreeningTime() + " at ";
			temp += movie.getScreenings().get(i).getBranch().getAddress() + "\n";
		screeningField.setText(temp);
		}
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
