package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class TicketsSalesReportController {
	private static List<SirtyaBranch> allBranches;
	

	public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> list) {
		TicketsSalesReportController.allBranches = list;
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
	private TableView<SirtyaBranch> branchesTable;

	@FXML
	private TableColumn<SirtyaBranch, String> branchCol;

	@FXML
	private TableColumn<SirtyaBranch, Integer> ticketsSoldCol;

	@FXML
	private TableColumn<SirtyaBranch, Double> totalIncomeCol;
	@FXML
	private TableColumn<SirtyaBranch, Integer> tabTicketsCol;


	ObservableList<SirtyaBranch> list = FXCollections.observableArrayList();

//	private void inBranch() {
//		Series<String, Double> series2 = new XYChart.Series<>();
//		for (SirtyaBranch branch : allBranches) {
//			String temp = branch.getAddress();
//			temp = temp.substring(temp.lastIndexOf(",") + 1);
//			series2.getData().add(new XYChart.Data<String, Double>(temp, branch.getTotalTicketsIncome()));
//
//		}
//		inBranchChart.getData().addAll(series2);
//	}

	@FXML
	void initialize() {
		EventBus.getDefault().register(this);
		branchCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, String>("address"));
		ticketsSoldCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, Integer>("totalTicketsSold"));
		totalIncomeCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, Double>("totalTicketsIncome"));
		tabTicketsCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, Integer>("totalTabTicketsSold"));

	}
	
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



	@Subscribe
	public void onLinksReportsEvent(LinksReportsEvent event) {

		Platform.runLater(() -> {
			list.clear();
			list.addAll((List<SirtyaBranch>) event.getBranchesList());
			branchesTable.getItems().clear();
			branchesTable.getItems().addAll(list);

		});
	}
	
}