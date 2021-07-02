package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.BarChart;
import javafx.scene.input.MouseEvent;

public class TicketsSalesReportController {
	private static List<SirtyaBranch> allBranches;
//	private static SirtyaBranch branch;
	

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
	private TableView<SirtyaBranch> branchesTable;

	@FXML
	private TableColumn<SirtyaBranch, String> branchCol;

	@FXML
	private TableColumn<SirtyaBranch, Integer> ticketsSoldCol;

	@FXML
	private TableColumn<SirtyaBranch, Double> totalIncomeCol;
	@FXML
	private TableColumn<SirtyaBranch, Integer> tabTicketsCol;

	@FXML
	private BarChart<String, Double> inBranchChart;

	@FXML
	private BarChart<String, Integer> inCompanyChart;

	ObservableList<SirtyaBranch> list = FXCollections
			.observableArrayList(TicketsSalesReportController.getAllBranches());

	private void inBranch() {
		Series<String, Double> series2 = new XYChart.Series<>();
		for (SirtyaBranch branch : allBranches) {
			String temp = branch.getAddress();
			temp = temp.substring(temp.lastIndexOf(",") + 1);
			series2.getData().add(new XYChart.Data<String, Double>(temp, branch.getTotalTicketsIncome()));

		}
		inBranchChart.getData().addAll(series2);
	}

	@FXML
	void initialize() {
		branchCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, String>("address"));
		ticketsSoldCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, Integer>("totalTicketsSold"));
		totalIncomeCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, Double>("totalTicketsIncome"));
		tabTicketsCol.setCellValueFactory(new PropertyValueFactory<SirtyaBranch, Integer>("totalTabTicketsSold"));
		branchesTable.setItems(list);
		inCompany();
		inBranch();

	}

//    @FXML
//    void setCelltoVar(MouseEvent event) {
//		SirtyaBranch temp = branchesTable.getItems().get(branchesTable.getSelectionModel().getSelectedIndex());
//		for(Movie movie : temp.getMovies()) {
//			if(movie.getClass().equals(CinemaMovie.class)) {
//				branchMovies.add((CinemaMovie)movie);
//				
//			}
//		}
//
//
//    }

	private void inCompany() {
		Series<String, Integer> series = new XYChart.Series<>();
		for (SirtyaBranch branch : allBranches) {
			String temp = branch.getAddress();
			temp = temp.substring(temp.lastIndexOf(",") + 1);
			series.getData().add(new XYChart.Data<String, Integer>(temp, branch.getTotalTicketsSold()));

		}
		inCompanyChart.getData().addAll(series);
	}

	
}