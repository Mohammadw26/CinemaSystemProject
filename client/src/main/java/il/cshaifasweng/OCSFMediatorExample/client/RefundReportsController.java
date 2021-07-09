package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Rent;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class RefundReportsController {
	private static List<SirtyaBranch> allBranches;
	private static List<Purchase> othersList;
	double linksCost = 0;

	public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> list) {
		RefundReportsController.allBranches = list;
	}

	ObservableList<SirtyaBranch> list = FXCollections.observableArrayList();

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
	private TableView<SirtyaBranch> branchesTable;

	@FXML
	private TableColumn<SirtyaBranch, String> branchCol;

	@FXML
	private TableColumn<SirtyaBranch, Double> totalIncomeCol;


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
		if (!EventBus.getDefault().isRegistered(allBranches)) {
			EventBus.getDefault().register(this);
		}

		branchCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, String>("address"));
		totalIncomeCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, Double>("totalPurpleRefund"));
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert ticketSalesBtn != null
				: "fx:id=\"ticketSalesBtn\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert otherSalesBtn != null
				: "fx:id=\"otherSalesBtn\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert refundsReportsBtn != null
				: "fx:id=\"refundsReportsBtn\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert complaintsBtn != null
				: "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert branchesTable != null
				: "fx:id=\"branchesTable\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert branchCol != null : "fx:id=\"branchCol\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert totalIncomeCol != null
				: "fx:id=\"totalIncomeCol\" was not injected: check your FXML file 'refundReports.fxml'.";
		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'refundReports.fxml'.";

	}

	@Subscribe
	public void onRefundReportsEvent(RefundReportsEvent event) {

		Platform.runLater(() -> {
			list.clear();
			list.addAll((List<SirtyaBranch>) event.getBranchesList());
			branchesTable.getItems().clear();
			branchesTable.getItems().addAll(list);

		});
	}
}
