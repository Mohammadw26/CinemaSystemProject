package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class TicketsSalesByBranchReportsController {

	private static List<SirtyaBranch> allBranches;

	public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> list) {
		TicketsSalesByBranchReportsController.allBranches = list;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	 @FXML
	    private Text currMonth;

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
	private Label totalTickets;

	@FXML
	private Label totalIncome;

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
		if (!EventBus.getDefault().isRegistered(this)) {
			EventBus.getDefault().register(this);
		}
		String month1 = LocalDate.now().getMonth().toString();
		currMonth.setText(month1);
		BranchManager manager = ((BranchManager) DisplayListController.getWorker());
		if (allBranches != null) {
			for (SirtyaBranch brnch : allBranches) {
				if (brnch.getId() == manager.getBranch().getId()) {
					totalIncome.setText("Total Income is: " + brnch.getTotalTicketsIncome());
					totalTickets.setText("Total Tickets number is: " + brnch.getTotalTicketsSold());
				}
			}

		}

		assert mainPane != null
				: "fx:id=\"mainPane\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert ticketSalesBtn != null
				: "fx:id=\"ticketSalesBtn\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert otherSalesBtn != null
				: "fx:id=\"otherSalesBtn\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert refundsReportsBtn != null
				: "fx:id=\"refundsReportsBtn\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert complaintsBtn != null
				: "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert totalTickets != null
				: "fx:id=\"totalTickets\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert totalIncome != null
				: "fx:id=\"totalIncome\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert backButton != null
				: "fx:id=\"backButton\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";

	}

	@Subscribe
	public void onLinksBranchesReportsEvent(LinksBranchesReportsEvent event) {
		Platform.runLater(() -> {
			allBranches = ((List<SirtyaBranch>) event.getBranchesList());
			initialize();
		});
	}

}
