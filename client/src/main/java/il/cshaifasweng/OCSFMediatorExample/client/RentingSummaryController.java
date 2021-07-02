package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.RentRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class RentingSummaryController {
	
	private static RentRequest request;
	
    public RentRequest getRequest() {
		return request;
	}

	public static void setRequest(RentRequest request) {
		RentingSummaryController.request = request;
	}
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="closeButton"
    private ToggleButton closeButton; // Value injected by FXMLLoader

    @FXML // fx:id="emailButton"
    private ToggleButton emailButton; // Value injected by FXMLLoader

    @FXML // fx:id="summray"
    private Label summray; // Value injected by FXMLLoader

    @FXML
    void closeStage(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void emailReminder(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'bookingSummary.fxml'.";
        assert emailButton != null : "fx:id=\"emailButton\" was not injected: check your FXML file 'bookingSummary.fxml'.";
        assert summray != null : "fx:id=\"summray\" was not injected: check your FXML file 'bookingSummary.fxml'.";
        String temp = ("Mr/Mrs " + request.getFirstName() + " " + request.getLastName() + "\n"
        		+ "Customer ID: " + request.getCustomerID() + "\nE-mail: " + request.getEmail()
        		+ "\nMovie: " + request.getMovie().getMovieTitle() + " - " + request.getMovie().getMovieTitleHeb());
			temp += ("\nTotal Cost: " + request.getMovie().getCost() + " NIS\nTransaction time: " + request.getTransactionTime()
			+"\n Start:" + request.getStreamingDatetime()
					+ "\n\nA link will be sent to you when the movie begins streaming\n"
					+ "We ask of you to be patient until then, Enjoy!");
			summray.setText(temp);
    }
}
