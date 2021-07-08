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
import il.cshaifasweng.OCSFMediatorExample.entities.Rent;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.TabPurchase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class RentLinksReportsController {
	private static List<Purchase> othersList;
	private static int linksPurchased = 0;
	private static int tabsPurchased = 0;
	private static double linksCost = 0;
	private static double tabsCost = 0;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label linksSold;

	@FXML
	private Label linksIncome;

	@FXML
	private Label tabsSold;

	@FXML
	private Label tabsIncome;

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
	private Text monthlinksField;

	@FXML
	private Text monthsTabField;

	@FXML
	void ComplaintsView(ActionEvent event) {

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
		linksPurchased = 0;
		tabsPurchased = 0;
		linksCost = 0;
		tabsCost = 0;
		String month1 = LocalDate.now().getMonth().toString();
		monthlinksField.setText(month1);
		monthsTabField.setText(month1);
		int month = LocalDate.now().getMonthValue();
		String date = String.format("%02d", month);
		monthlinksField.setText(date);
		monthsTabField.setText(date);
		if (othersList != null) {
			for (Purchase purchase : othersList) {
				if (purchase.getClass().equals(Rent.class)) {
//					if(purchase.getTransactionTime().substring(3, 5) == date) {
					linksPurchased++;
					linksCost = linksCost + ((Rent) purchase).getCost();
//					}
				}
				if (purchase.getClass().equals(TabPurchase.class)) {
					tabsPurchased++;
					tabsCost = ((TabPurchase) purchase).getCost();
				}
			}
		}

		linksIncome.setText("Links Income\n" + linksCost);
		linksSold.setText("Links Sold\n" + linksPurchased);
		tabsIncome.setText("Tabs Income\n" + tabsCost);
		tabsSold.setText("Tabs Sold\n" + tabsPurchased);

		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert ticketSalesBtn != null
				: "fx:id=\"ticketSalesBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert otherSalesBtn != null
				: "fx:id=\"otherSalesBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert refundsReportsBtn != null
				: "fx:id=\"refundsReportsBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert complaintsBtn != null
				: "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert backButton != null
				: "fx:id=\"backButton\" was not injected: check your FXML file 'rentLinksReports.fxml'.";

	}

	public static List<Purchase> getOthersList() {
		return othersList;
	}

	public static void setOthersList(List<Purchase> othersList) {
		RentLinksReportsController.othersList = othersList;
	}

	@Subscribe
	public void onTabReportsEvent(TabReportsEvent event) {
		Platform.runLater(() -> {
			othersList = ((List<Purchase>) event.getPurchasesListDemand());
			initialize();
		});
	}

}
