package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Rent;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.TabPurchase;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;

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
	void initialize() {
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

	}

	public static List<Purchase> getOthersList() {
		return othersList;
	}

	public static void setOthersList(List<Purchase> othersList) {
		RentLinksReportsController.othersList = othersList;
	}

	private void inLinks() {
		Series<String, Double> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<String, Double>("Links Purchased", (double) linksPurchased));
		series.getData().add(new XYChart.Data<String, Double>("Links Income", (double) linksCost));
		linksChart.getData().addAll(series);
	}

	private void inTabs() {
		Series<String, Double> series2 = new XYChart.Series<>();
		series2.getData().add(new XYChart.Data<String, Double>("Tabs Purchased", (double) tabsPurchased));
		series2.getData().add(new XYChart.Data<String, Double>("Tabs Income", (double) tabsPurchased * tabsCost));
		tabsChart.getData().addAll(series2);
	}

}
