package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AddMovieScreeningController {
	private static CinemaMovie movie;
	private static List<SirtyaBranch> allBranches;
	private static SirtyaBranch branch;
	

    public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> list) {
		AddMovieScreeningController.allBranches = list;
	}

	public static CinemaMovie getMovie() {
		return movie;
	}

	public static void setMovie(CinemaMovie set) {
		movie = set;
	}
    @FXML
    private Label TitleField;

    @FXML
    private Label screeningsField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeField;

    @FXML
    private Button applyBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> branchCombo;
    
    @FXML
    private TableView<Screening> screeningsTable;
    
    @FXML
    private TableColumn<Screening,Integer> idCol;

    @FXML
    private TableColumn<Screening, String> dateCol;

    @FXML
    private TableColumn<Screening, String> timeCol;

    @FXML
    private TableColumn<Screening, String> branchCol;

    @FXML
    void ApplyChanges(ActionEvent event) {
    	ScreeningsUpdateRequest request = new ScreeningsUpdateRequest();
    	request.setDate(dateField.getText());
    	request.setTime(timeField.getText());
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

    @FXML
    void ChooseBranch(ActionEvent event) {
		for (SirtyaBranch brnch : allBranches) {
			if (brnch.getAddress()==branchCombo.getValue()) {
				branch = brnch;
			}
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
    void setCelltoField(MouseEvent event) {
    	Screening temp = screeningsTable.getItems().get(screeningsTable.getSelectionModel().getSelectedIndex());
        timeField.setText(temp.getScreeningTime());
        dateField.setText(temp.getScreeningDate());

 //       dateField.setValue(temp.getScreeningTime().substring(0, 2));
//        minuteField.setValue(temp.getScreeningTime().substring(3, 5));
//        dayField.setValue(temp.getScreeningDate().substring(0, 2));
//        monthField.setValue(temp.getScreeningDate().substring(3, 5));
//        yearField.setValue(temp.getScreeningDate().substring(6, 10));
    }
    
    
    ObservableList<Screening> screeningList = FXCollections.observableArrayList(movie.getScreenings());

    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	TitleField.setText(movie.getMovieTitle());
    	TitleField.setText(movie.getMovieTitle());
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	timeCol.setCellValueFactory(new PropertyValueFactory<>("screeningTime"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("screeningDate"));
    	branchCol.setCellValueFactory(new PropertyValueFactory<>("screeningBranch"));
    	screeningsTable.setItems(screeningList);
		for (SirtyaBranch brnch : allBranches) {
			branchCombo.getItems().addAll(brnch.getAddress());
		}
    }

}

