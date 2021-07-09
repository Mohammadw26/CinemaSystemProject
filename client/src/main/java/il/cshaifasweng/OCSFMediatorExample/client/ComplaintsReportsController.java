package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class ComplaintsReportsController {
	private static List<SirtyaBranch> allBranches;
	

	public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> list) {
		ComplaintsReportsController.allBranches = list;
	}

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
    private BarChart<String, Integer> complaintsChart;

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
	
	
	private void inBranch() {
		Series<String, Integer> series2 = new XYChart.Series<>();
		for (SirtyaBranch branch : allBranches) {
			String temp = branch.getAddress();
			temp = temp.substring(temp.lastIndexOf(",") + 1);
			series2.getData().add(new XYChart.Data<String, Integer>(temp, branch.getBranchComplaintNum()));

		}
		series2.getData().add(new XYChart.Data<String, Integer>("General Complaints", SirtyaBranch.getGeneralComplaintNum()));
		complaintsChart.getData().addAll(series2);
	}

    @FXML
    void initialize() {
    	inBranch();
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'complaintsReports.fxml'.";
        assert ticketSalesBtn != null : "fx:id=\"ticketSalesBtn\" was not injected: check your FXML file 'complaintsReports.fxml'.";
        assert otherSalesBtn != null : "fx:id=\"otherSalesBtn\" was not injected: check your FXML file 'complaintsReports.fxml'.";
        assert refundsReportsBtn != null : "fx:id=\"refundsReportsBtn\" was not injected: check your FXML file 'complaintsReports.fxml'.";
        assert complaintsBtn != null : "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'complaintsReports.fxml'.";
        assert complaintsChart != null : "fx:id=\"complaintsChart\" was not injected: check your FXML file 'complaintsReports.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'complaintsReports.fxml'.";

    }
}
