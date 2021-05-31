package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class DisplayMovieDataController {
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

    @FXML // fx:id="editBtn"
    private Button editBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addBtn"
    private Button addBtn; // Value injected by FXMLLoader

    @FXML // fx:id="deleteBtn"
    private Button deleteBtn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert imageField != null : "fx:id=\"imageField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert titleField != null : "fx:id=\"titleField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert descriptionField != null : "fx:id=\"descriptionField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert ProducersField != null : "fx:id=\"ProducersField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert starsField != null : "fx:id=\"starsField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert costField != null : "fx:id=\"costField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert screeningField != null : "fx:id=\"screeningField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert editBtn != null : "fx:id=\"editBtn\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'displayMovieData.fxml'.";
    }
    
	public void setDisplay () {
		titleField.setText(movie.getMovieTitle());
		descriptionField.setText("" + movie.getMovieDescription());
		ProducersField.setText("Movie Producers: " +movie.getMovieProducer());
		starsField.setText("Starring Actors: " +movie.getStarringActors());
		costField.setText("Cost per Ticket: " +movie.getTicketCost() + "$");
		counter = movie.getScreenings().size();
		for (int i = 0 ; i < counter; i++) {
			temp += movie.getScreenings().get(i).getScreeningDate() + " ";
			temp += movie.getScreenings().get(i).getScreeningTime() + " at ";
			temp += movie.getScreenings().get(i).getBranch().getAddress() + "\n";
		screeningField.setText(temp);
		}
		//try {
			/*InputStream stream = new FileInputStream(movie.getImage().getImgURL());
			Image image = new Image(stream);*/
			//imageField = ImageViewBuilder.create().image(new Image(movie.getImage().getImgURL())).build();
			Image image = new Image(movie.getImage().getImgURL());
			imageField.setImage(image);
			imageField.setPreserveRatio(false);
		/*} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
//    @FXML
//    void uploadAddScreening(ActionEvent event) {
//    	AddMovieScreeningController.setMovie(movie);
//		try {
//			SimpleClient.getClient().sendToServer("#BranchesListRequest");
//	    	} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//
//    @FXML
//    void uploadDeleteScreening(ActionEvent event) {
//    	EditMovieScreeningsController.setMovie(movie);
//    	try {
//			App.setRoot("deleteMovieScreening");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }

    @FXML
    void uploadEditScreening(ActionEvent event) {
    	EditMovieScreeningsController.setMovie(movie);
    	try {
			SimpleClient.getClient().sendToServer("#BranchesListRequest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
