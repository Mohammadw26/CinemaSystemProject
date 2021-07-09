package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ReportsReviewController {
	private static List<Purchase> othersList;
	private static List<SirtyaBranch> allBranches;

	
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
	    	try {
				SimpleClient.getClient().sendToServer("#ComplaintsReportsRequest");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	@FXML
	void OtherSalesView(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#ReportsRequest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	@FXML
	void RefundsView(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#RefundReportsRequest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void TicketSalesView(ActionEvent event) {
		if (DisplayListController.getWorker().getClass().equals(GeneralManager.class)) {
			try {
				SimpleClient.getClient().sendToServer("#TicketsReportsRequest");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if (DisplayListController.getWorker().getClass().equals(BranchManager.class)) {
			try {
				SimpleClient.getClient().sendToServer("#BranchesTicketsReportsRequest");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

//	private void loadUI(String ui) {
//		Parent root = null;
//		try {
//			root = FXMLLoader.load(getClass().getResource(ui));
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		mainPane.setCenter(root);
//	}
	
//	@Subscribe
//	public void onReportsViewEvent(ReportsViewEvent event) {
//		Platform.runLater(()->{
//			TicketsSalesReportController.setAllBranches(event.getBranchesList());
//			RentLinksReportsController.setOthersList((List<Purchase>) event.getPurchasesListDemand());
//			TicketSalesView(null);
//		});
//	}

	public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> allBranches) {
		ReportsReviewController.allBranches = allBranches;
	}

	public static List<Purchase> getOthersList() {
		return othersList;
	}

	public static void setOthersList(List<Purchase> othersList) {
		ReportsReviewController.othersList = othersList;
	}
	
}
