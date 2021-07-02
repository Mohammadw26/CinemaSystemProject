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

public class RentLinksReportsController {
	private static List<Purchase> othersList;
	private static int linksPurchased = 0;
	private static int tabsPurchased = 0;
	private static double linksCost = 0;
	private static double tabsCost = 0;
	Series<String, Double> series = new XYChart.Series<>();
	Series<String, Double> series2 = new XYChart.Series<>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BarChart<String, Double> tabsChart;

	@FXML
	private BarChart<String, Double> linksChart;

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
				App.setRoot("ticketsSalesReport");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if (DisplayListController.getWorker().getClass().equals(BranchManager.class)) {
			try {
				App.setRoot("ticketsSalesByBranchReports");
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
		if(!EventBus.getDefault().isRegistered(this)) {
			EventBus.getDefault().register(this);
		}
		if (othersList != null) {
			for (Purchase purchase : othersList) {
				if (purchase.getClass().equals(Rent.class)) {
					linksPurchased++;
					linksCost = linksCost + ((Rent) purchase).getCost();
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

		inLinks();
		inTabs();
		assert tabsChart != null
				: "fx:id=\"tabsChart\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert linksChart != null
				: "fx:id=\"linksChart\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert ticketSalesBtn != null
				: "fx:id=\"ticketSalesBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert otherSalesBtn != null
				: "fx:id=\"otherSalesBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert refundsReportsBtn != null
				: "fx:id=\"refundsReportsBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert complaintsBtn != null
				: "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'rentLinksReports.fxml'.";
		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'rentLinksReports.fxml'.";

	}

	public static List<Purchase> getOthersList() {
		return othersList;
	}

	public static void setOthersList(List<Purchase> othersList) {
		RentLinksReportsController.othersList = othersList;
	}

	private void inLinks() {
		series.getData().add(new XYChart.Data<String, Double>("Links Purchased", (double) linksPurchased));
		series.getData().add(new XYChart.Data<String, Double>("Links Income", (double) linksCost));
		linksChart.getData().addAll(series);
	}

	private void inTabs() {
		series2.getData().add(new XYChart.Data<String, Double>("Tabs Purchased", (double) tabsPurchased));
		series2.getData().add(new XYChart.Data<String, Double>("Tabs Income", (double) tabsPurchased * tabsCost));
		tabsChart.getData().addAll(series2);
	}
	
	@Subscribe
	public void onLinksReportsEvent(LinksReportsEvent event) {
		Platform.runLater(()->{
			othersList = ((List<Purchase>) event.getPurchasesListDemand());
			initialize();
		});
	}
	

}
