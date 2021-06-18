package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class BookingSummaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text nameSummary;

    @FXML
    private Text filmSummary;

    @FXML
    private Text dateSummary;

    @FXML
    private Text timeSummary;

    @FXML
    private Text seatSummary;

    @FXML
    private ToggleButton closeButton;

    @FXML
    private ToggleButton emailButton;

    @FXML
    void closeStage(ActionEvent event) {
    	try {
			App.setRoot("primary");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void emailReminder(ActionEvent event) {
    		// send email

    	try {
			App.setRoot("primary");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert nameSummary != null : "fx:id=\"nameSummary\" was not injected: check your FXML file 'BookingSummary.fxml'.";
        assert filmSummary != null : "fx:id=\"filmSummary\" was not injected: check your FXML file 'BookingSummary.fxml'.";
        assert dateSummary != null : "fx:id=\"dateSummary\" was not injected: check your FXML file 'BookingSummary.fxml'.";
        assert timeSummary != null : "fx:id=\"timeSummary\" was not injected: check your FXML file 'BookingSummary.fxml'.";
        assert seatSummary != null : "fx:id=\"seatSummary\" was not injected: check your FXML file 'BookingSummary.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'BookingSummary.fxml'.";
        assert emailButton != null : "fx:id=\"emailButton\" was not injected: check your FXML file 'BookingSummary.fxml'.";

    }
}
