package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class BookingSummaryController {

	private static FullOrderRequest request;
	
	
    public FullOrderRequest getRequest() {
		return request;
	}

	public static void setRequest(FullOrderRequest request) {
		BookingSummaryController.request = request;
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

    }

    @FXML
    void emailReminder(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'bookingSummary.fxml'.";
        assert emailButton != null : "fx:id=\"emailButton\" was not injected: check your FXML file 'bookingSummary.fxml'.";
        assert summray != null : "fx:id=\"summray\" was not injected: check your FXML file 'bookingSummary.fxml'.";
        BookingRequest request2 = request.getRequest();
        String temp = ("Mr/Mrs " + request.getFirstName() + " " + request.getLastName() + "\n"
        		+ "Customer ID: " + request.getCustomerID() + "\nE-mail: " + request.getEmail()
        		+ "\nMovie: " + request2.getScreening().getMovie().getMovieTitle() + " - " + request2.getScreening().getMovie().getMovieTitleHeb()
        		+"\nScreening Time: " + request2.getScreening().getScreeningDate() + " , " + request2.getScreening().getScreeningTime()
        		+"\nNumber Of Seats: " + request2.getArrSize() + "\nSeats IDs: ");
			for (int i = 0; i < request2.getArrSize(); i++) {
				temp += request2.getSeatIds()[i] + " ";
			}
			temp += ("\nTotal Cost: " + request2.getScreening().getMovie().getTicketCost()*request2.getArrSize() + " NIS");
			summray.setText(temp);
    }
}
