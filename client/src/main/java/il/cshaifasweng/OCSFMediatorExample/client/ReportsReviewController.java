package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.jboss.logging.Logger;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ReportsReviewController {

	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane mainPane;

	@FXML
	private Button ticketSalesBtn;

	@FXML
	private Button otherSalesBtn;

	@FXML
	private Button refundsReportsBtn;

	@FXML
	private Button complaintsBtn;

	@FXML
	private Button backButton;

	@FXML
	void ComplaintsView(ActionEvent event) {

	}

	@FXML
	void OtherSalesView(ActionEvent event) {
		loadUI("rentLinksReports.fxml");
}

	@FXML
	void RefundsView(ActionEvent event) {

	}

	@FXML
	void TicketSalesView(ActionEvent event) {
		if (DisplayListController.getWorker().getClass().equals(GeneralManager.class)) {
			loadUI("ticketsSalesReport.fxml");
		} else if (DisplayListController.getWorker().getClass().equals(BranchManager.class)) {
			loadUI("ticketsSalesByBranchReports.fxml");

		}
	}

	@FXML
	void backToHome(ActionEvent event) {
		try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void initialize() {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'reportsReview.fxml'.";
		assert ticketSalesBtn != null
				: "fx:id=\"ticketSalesBtn\" was not injected: check your FXML file 'reportsReview.fxml'.";
		assert otherSalesBtn != null
				: "fx:id=\"otherSalesBtn\" was not injected: check your FXML file 'reportsReview.fxml'.";
		assert refundsReportsBtn != null
				: "fx:id=\"refundsReportsBtn\" was not injected: check your FXML file 'reportsReview.fxml'.";
		assert complaintsBtn != null
				: "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'reportsReview.fxml'.";
		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'reportsReview.fxml'.";

	}

	private void loadUI(String ui) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(ui));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		mainPane.setCenter(root);
	}
}
