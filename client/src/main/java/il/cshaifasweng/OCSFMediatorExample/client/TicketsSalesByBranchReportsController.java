package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;

public class TicketsSalesByBranchReportsController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BarChart<String, Double> inBranchChart;

	@FXML
	private Label totalTickets;

	@FXML
	private Label totalIncome;

	@FXML
	void initialize() {
		inBranch();
		assert inBranchChart != null
				: "fx:id=\"inBranchChart\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert totalTickets != null
				: "fx:id=\"totalTickets\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";
		assert totalIncome != null
				: "fx:id=\"totalIncome\" was not injected: check your FXML file 'ticketsSalesByBranchReports.fxml'.";

	}

	private void inBranch() {
		Series<String,Double>series = new XYChart.Series<>();
		BranchManager manager = ((BranchManager)DisplayListController.getWorker());
		totalIncome.setText(manager.getWokerUsername());
//		totalIncome.setText("Branch Income: " + manager.getBranch().getTotalTicketsIncome() );
		totalTickets.setText("Tickets Sold: " + manager.getBranch().getTotalTicketsSold());
		String temp = manager.getBranch().getAddress();
		temp = temp.substring(temp.lastIndexOf(",")+1);
		series.getData().add(new XYChart.Data<String, Double>(temp,(double)(manager.getBranch().getTotalTicketsSold())));
		series.getData().add(new XYChart.Data<String, Double>(temp,(double)(manager.getBranch().getTotalTicketsIncome())));
		inBranchChart.getData().addAll(series);

		}

}
