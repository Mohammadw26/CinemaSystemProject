package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.ScreeningsUpdateRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class DeleteMovieScreeningController {
	private static Movie movie;
	private int counter = 0;
	private String temp = "";

    public static Movie getMovie() {
		return movie;
	}

	public static void setMovie(Movie set) {
		movie = set;
	}

	@FXML
    private Label TitleField;

    @FXML
    private Label screeningsField;

    @FXML
    private TextField idField;

    @FXML
    private Button applyBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void ApplyChanges(ActionEvent event) {
    	ScreeningsUpdateRequest request = new ScreeningsUpdateRequest(Integer.parseInt(idField.getText()));
    	request.setMovieID(movie.getId());
    	request.setMovie(movie);
    	try {
			SimpleClient.getClient().sendToServer(new Message("#DeleteScreening",request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ReturnToCatalog(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	TitleField.setText(movie.getMovieTitle());
        counter = movie.getScreenings().size();
		for (int i = 0 ; i < counter; i++) {
			temp += movie.getScreenings().get(i).getScreeningDate() + " ";
			temp += movie.getScreenings().get(i).getScreeningTime() + " at ";
			temp += movie.getScreenings().get(i).getBranch().getAddress() + " -- Screening ID: " + movie.getScreenings().get(i).getId() + "\n";
		}
		screeningsField.setText(temp);
    }
}
