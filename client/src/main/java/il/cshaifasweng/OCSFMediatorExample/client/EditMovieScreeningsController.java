package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.ScreeningsUpdateRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;

public class EditMovieScreeningsController {
	private static Movie movie;

	private static List<SirtyaBranch> allBranches;
	private static SirtyaBranch branch;

	public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> list) {
		EditMovieScreeningsController.allBranches = list;
	}

	@FXML
	void ChooseBranch(ActionEvent event) {
		for (SirtyaBranch brnch : allBranches) {
			if (brnch.getAddress() == branchField.getValue()) {
				branch = brnch;
			}
		}
	}

	public static Movie getMovie() {
		return movie;
	}

	public static void setMovie(Movie set) {
		movie = set;
	}

	@FXML
	private Label TitleField;

//    @FXML
//    private Label screeningsField;

	@FXML
	private TextField idField;

	@FXML
	private TextField dateField;

	@FXML
	private Button applyBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private ComboBox<String> dayField;

	@FXML
	private ComboBox<String> monthField;

	@FXML
	private ComboBox<String> yearField;

	@FXML
	private ComboBox<String> hourField;

	@FXML
	private ComboBox<String> minuteField;

	@FXML
	private TableView<Screening> screeningsTable;

	@FXML
	private TableColumn<Screening, Integer> idCol;

	@FXML
	private TableColumn<Screening, String> dateCol;

	@FXML
	private TableColumn<Screening, String> timeCol;

	@FXML
	private TableColumn<Screening, String> branchCol;

	@FXML
	private ComboBox<String> optionField;

	@FXML
	private TextField priceField;

	@FXML
	private ComboBox<String> branchField;

	@FXML
	private Label editMessage;

	@FXML
	private Label deleteMessage;

	@FXML
	private DatePicker datePick;

	@FXML
	void ApplyChanges(ActionEvent event) {
		if (optionField.getValue() == "Edit screening") {
			ScreeningsUpdateRequest request = new ScreeningsUpdateRequest();
			request.setScrnID(Integer.parseInt(idField.getText()));
			request.setDate(datePick.getEditor().getText());
			request.setTime(hourField.getValue() + ":" + minuteField.getValue());
			request.setMovieID(movie.getId());
			request.setMovie(movie);

			try {
				SimpleClient.getClient().sendToServer(new Message("#EditScreening", request));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (optionField.getValue() == "Delete screening" ) {
	    	ScreeningsUpdateRequest request = new ScreeningsUpdateRequest(Integer.parseInt(idField.getText()));
			request.setMovieID(movie.getId());
	    	request.setMovie(movie);
	    	try {
				SimpleClient.getClient().sendToServer(new Message("#DeleteScreening",request));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(optionField.getValue() == "Add screening" ){
			ScreeningsUpdateRequest request = new ScreeningsUpdateRequest();
			request.setDate(datePick.getEditor().getText());
			request.setTime(hourField.getValue() + ":" + minuteField.getValue());
	    	request.setMovie(movie);
	    	request.setBranch(branch);
	    	request.setMovieID(movie.getId());
	    	request.setMovie(movie);

	    	try {
				SimpleClient.getClient().sendToServer(new Message("#AddScreening",request));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(optionField.getValue() == "change price" ){
			
			
		}
	}

	@FXML
	void ReturnToCatalog(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void getOptionField(ActionEvent event) {
//		dayField.setDisable(true);
//		monthField.setDisable(true);
//		yearField.setDisable(true);
		hourField.setDisable(true);
		minuteField.setDisable(true);
		branchField.setDisable(true);
		priceField.setDisable(true);
		editMessage.setVisible(false);
		deleteMessage.setVisible(false);
		datePick.setDisable(true);
		if (optionField.getValue() == "Change price" || optionField.getValue() == "Add screening"
				|| optionField.getValue() == "Delete screening") {
			hourField.setValue("Select");
			minuteField.setValue("Select");
			datePick.getEditor().setText("");
//			dayField.setValue("Select");
//			monthField.setValue("Select");
//			yearField.setValue("Select");
			if (optionField.getValue() == "Change price") {
				priceField.setDisable(false);
			}
			if (optionField.getValue() == "Delete screening") {
				deleteMessage.setVisible(true);

			}
		}
		if ((optionField.getValue() == "Add screening") || (optionField.getValue() == "Edit screening")) {
//			dayField.setDisable(false);
//			monthField.setDisable(false);
//			yearField.setDisable(false);
			datePick.setDisable(false);
			hourField.setDisable(false);
			minuteField.setDisable(false);
			if (optionField.getValue() == "Edit screening") {
				editMessage.setVisible(true);

			}
			if (optionField.getValue() == "Add screening") {
				branchField.setDisable(false);

			}
		}
	}

	@FXML
	void setCelltoField(MouseEvent event) {
		Screening temp = screeningsTable.getItems().get(screeningsTable.getSelectionModel().getSelectedIndex());
		idField.setText(Integer.toString(temp.getId()));
		if (optionField.getValue() == "Edit screening") {
			hourField.setValue(temp.getScreeningTime().substring(0, 2));
			minuteField.setValue(temp.getScreeningTime().substring(3, 5));
//			dayField.setValue(temp.getScreeningDate().substring(0, 2));
//			monthField.setValue(temp.getScreeningDate().substring(3, 5));
//			yearField.setValue(temp.getScreeningDate().substring(6, 10));
			datePick.getEditor().setText(temp.getScreeningDate());
		}
	}

	ObservableList<Screening> screeningList = FXCollections.observableArrayList(movie.getScreenings());

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		TitleField.setText(movie.getMovieTitle());
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		timeCol.setCellValueFactory(new PropertyValueFactory<>("screeningTime"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("screeningDate"));
		branchCol.setCellValueFactory(new PropertyValueFactory<>("screeningBranch"));
		screeningsTable.setItems(screeningList);
		for (int i = 0; i < 10; i++) {
			hourField.getItems().add("0" + Integer.toString(i));
			minuteField.getItems().add("0" + Integer.toString(i));
			dayField.getItems().add("0" + Integer.toString(i));
			monthField.getItems().add("0" + Integer.toString(i));

		}
		for (int i = 10; i < 24; i++) {
			hourField.getItems().addAll(Integer.toString(i));
		}
		for (int i = 10; i < 60; i++) {
			minuteField.getItems().addAll(Integer.toString(i));
		}
		for (int i = 10; i < 13; i++) {
			monthField.getItems().addAll(Integer.toString(i));
		}
		for (int i = 10; i < 32; i++) {
			dayField.getItems().addAll(Integer.toString(i));
		}
		for (int i = 2021; i < 2050; i++) {
			yearField.getItems().addAll(Integer.toString(i));
		}
		optionField.getItems().addAll("Edit screening", "Delete screening", "Add screening", "Change price");
		priceField.setText(Double.toString(movie.getTicketCost()));
		for (SirtyaBranch brnch : allBranches) {
			branchField.getItems().addAll(brnch.getAddress());
		}
	}

}
