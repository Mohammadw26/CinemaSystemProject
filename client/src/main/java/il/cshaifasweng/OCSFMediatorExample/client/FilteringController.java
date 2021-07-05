/**
 * Sample Skeleton for 'searchFilters.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.Parent;

public class FilteringController {
	private static List<Movie> allMovies;
	private static List<SirtyaBranch> allBranches;
	private List<Movie> filtered_list = new ArrayList<Movie>();
	private int page;
	private int pages;
	private int type;

    public static List<Movie> getAllMovies() {
		return allMovies;
	}

	public static void setAllMovies(List<Movie> allMovies) {
		FilteringController.allMovies = allMovies;
	}

	public static List<SirtyaBranch> getAllBranches() {
		return allBranches;
	}

	public static void setAllBranches(List<SirtyaBranch> allBranches) {
		FilteringController.allBranches = allBranches;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="typeCombo"
    private ComboBox<String> typeCombo; // Value injected by FXMLLoader

    @FXML // fx:id="fieldText"
    private TextField fieldText; // Value injected by FXMLLoader

    @FXML // fx:id="branchCombo"
    private ComboBox<String> branchCombo; // Value injected by FXMLLoader

    @FXML // fx:id="date1"
    private DatePicker date1; // Value injected by FXMLLoader

    @FXML // fx:id="date2"
    private DatePicker date2; // Value injected by FXMLLoader

    @FXML // fx:id="h1"
    private ComboBox<String> h1; // Value injected by FXMLLoader

    @FXML // fx:id="m1"
    private ComboBox<String> m1; // Value injected by FXMLLoader

    @FXML // fx:id="h2"
    private ComboBox<String> h2; // Value injected by FXMLLoader

    @FXML // fx:id="m2"
    private ComboBox<String> m2; // Value injected by FXMLLoader

    @FXML // fx:id="searchBtn"
    private Button searchBtn; // Value injected by FXMLLoader
    
    @FXML // fx:id="warningLabel"
    private Label warningLabel; // Value injected by FXMLLoader

    @FXML // fx:id="listGrid"
    private GridPane listGrid; // Value injected by FXMLLoader

    @FXML // fx:id="resultsLabel"
    private Label resultsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="waitAnchor"
    private AnchorPane waitAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="prevIcon"
    private FontAwesomeIconView prevIcon; // Value injected by FXMLLoader

    @FXML // fx:id="nextIcon"
    private FontAwesomeIconView nextIcon; // Value injected by FXMLLoader
    
    @FXML
    private Button resetBtn;

    @FXML
    void back(ActionEvent event) {
		try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void disableFields(ActionEvent event) {
    	if (typeCombo.getValue() == "On-Demand" || typeCombo.getValue() == "Coming Soon") {
        	date1.setDisable(true);
        	h1.setDisable(true);
        	m1.setDisable(true);
        	date2.setDisable(true);
        	h2.setDisable(true);
        	m2.setDisable(true);
        	branchCombo.setDisable(true);
    	} else if (typeCombo.getValue() == "Cinema Screenings") {
        	date1.setDisable(false);
        	h1.setDisable(false);
        	m1.setDisable(false);
        	date2.setDisable(false);
        	h2.setDisable(false);
        	m2.setDisable(false);
        	branchCombo.setDisable(false);
    	}
    }

    @FXML
    void next(MouseEvent event) {
		if (page < filtered_list.size() / 3 + 1) {
			page++;
			try {
					fillGrids(listGrid,type);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			prevIcon.setVisible(true);
		} 
		if (page == filtered_list.size() / 3 + 1) {
			nextIcon.setVisible(false);
		}
    }

    @FXML
    void prev(MouseEvent event) {
		if (page > 1) {
			page--;
			try {
					fillGrids(listGrid,type);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nextIcon.setVisible(true);
		}
		if (page == 1) {
			prevIcon.setVisible(false);
		}
    }

    @FXML
    void search(ActionEvent event) {
    	if ((h1.getValue()!=null || h2.getValue()!=null || m1.getValue()!=null || m2.getValue()!= null)
    			&& !(h1.getValue()!=null && h2.getValue()!=null && m1.getValue()!=null && m2.getValue()!= null)) {
    		warningLabel.setText("If you would like to narrow the search for specific screening hours, please fill all the time fields. otherwise, free the time fields.");
    		warningLabel.setVisible(true);
    		return;
    	}
    	if ((date1.getValue()!=null && date2.getValue()==null) || (date1.getValue()==null && date2.getValue()!=null)) {
    		warningLabel.setText("If you would like to narrow the search for specific dates, please choose two dates. otherwise, free the date fields.");
    		warningLabel.setVisible(true);
    		return;
    	}
    	if (date1.getEditor().getText()=="" || date2.getEditor().getText()=="") {
    		date1.setValue(null);
    		date2.setValue(null);
    	}
    	if (h2.getValue()=="" || h2.getValue()=="" || m1.getValue()=="" || m2.getValue()=="") {
    		h1.setValue(null);
    		h2.setValue(null);
    		m1.setValue(null);
    		m2.setValue(null);
    	}
		nextIcon.setVisible(false);
		prevIcon.setVisible(false);
    	waitAnchor.setVisible(true);
//    	try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    	listGrid.setVisible(true);
    	filtered_list.clear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    	if (typeCombo.getValue() == "On-Demand") {
    		type = 2;
    		if (fieldText.getText() != null || fieldText.getText() != "") {
    			for (Movie movie : allMovies) {
    				if (movie.getClass() == OnDemandMovie.class &&  movie.getMovieTitle().toLowerCase().contains(fieldText.getText().toLowerCase())){
    					filtered_list.add(movie);
    				}
    			}
    		} else {
    			for (Movie movie : allMovies) {
    				if (movie.getClass() == OnDemandMovie.class){
    					filtered_list.add(movie);
    				}
    			}
    		}
    	} else     	if (typeCombo.getValue() == "Coming Soon") {
    		type = 3;
    		if (fieldText.getText() != null || fieldText.getText() != "") {
    			for (Movie movie : allMovies) {
    				if (movie.getClass() == ComingSoonMovie.class &&  movie.getMovieTitle().toLowerCase().contains(fieldText.getText().toLowerCase())){
    					filtered_list.add(movie);
    				}
    			}
    		} else {
    			for (Movie movie : allMovies) {
    				if (movie.getClass() == ComingSoonMovie.class){
    					filtered_list.add(movie);
    				}
    			}
    		}
    	}
    	else if (typeCombo.getValue() == "Cinema Screenings") {
    		type = 1;
    		if (fieldText.getText() != null || fieldText.getText() != "") {
    			for (Movie movie : allMovies) {
    				if (movie.getClass() == CinemaMovie.class &&  movie.getMovieTitle().toLowerCase().contains(fieldText.getText().toLowerCase())){
    					filtered_list.add(movie);
    				}
    			}
    		} else {
    			for (Movie movie : allMovies) {
    				if (movie.getClass() == CinemaMovie.class){
    					filtered_list.add(movie);
    				}
    			}
    		}
    		if (date1.getValue()!=null && date2.getValue()!=null) {
    			for (int i = 0 ; i < filtered_list.size(); i++) {
    				Movie movie = filtered_list.get(i);
    				List<Screening> tempList = ((CinemaMovie)movie).getScreenings();
    				int counter = 0;
    				for (Screening screening : tempList) {
    					LocalDate tempDate = LocalDate.parse(screening.getScreeningDate(),formatter);
    					if ( (screening.getBranch().getAddress() == branchCombo.getValue() || branchCombo.getValue() == "All" || branchCombo.getValue() == "" ||branchCombo.getValue() == null )
    							&& ( ( tempDate.isAfter( date1.getValue().minusDays(1) ) ) && ( tempDate.isBefore( date2.getValue().plusDays(1) ) ) ) ){
    						if (h1.getValue()!=null && h2.getValue()!=null && m1.getValue()!=null && m2.getValue()!= null) {
    							LocalDateTime tempTime = LocalDateTime.parse("01/01/2021 " + screening.getScreeningTime(), formatter2);
    							LocalDateTime startTime = LocalDateTime.parse("01/01/2021 " + h1.getValue() + ":" + m1.getValue(), formatter2);
    							LocalDateTime endTime = LocalDateTime.parse("01/01/2021 " + h2.getValue() + ":" + m2.getValue(), formatter2);
    							if (tempTime.isAfter(startTime.minusMinutes(1)) && tempTime.isBefore(endTime.plusMinutes(1))) {
    								counter++;
    							}
    						} else {
    							counter ++;
    						}
    					}
    				}
    				if (counter<=0) {
    					filtered_list.remove(movie);
    					i--;
    				}
    			}
    		} else {
    			if (branchCombo.getValue() != "All" && branchCombo.getValue() != "" && branchCombo.getValue() != null) {
    				for (int i = 0 ; i < filtered_list.size(); i++) {
    					Movie movie = filtered_list.get(i);
    					List<Screening> tempList = ((CinemaMovie)movie).getScreenings();
    					int counter = 0;
    					for (Screening screening : tempList) {
    						if (screening.getBranch().getAddress() == branchCombo.getValue()) {
    							counter++;
    						}
    					}
    					if (counter<=0) {
    						filtered_list.remove(movie);
    						i--;
    					}
    				}
    			} 
    			if (h1.getValue()!=null && h2.getValue()!=null && m1.getValue()!=null && m2.getValue()!= null) {
    				for (int i = 0 ; i < filtered_list.size(); i++) {
    					Movie movie = filtered_list.get(i);
    					List<Screening> tempList = ((CinemaMovie)movie).getScreenings();
    					int counter = 0;
    					for (Screening screening : tempList) {
    						LocalDateTime tempTime = LocalDateTime.parse("01/01/2021 " + screening.getScreeningTime(), formatter2);
    						LocalDateTime startTime = LocalDateTime.parse("01/01/2021 " + h1.getValue()+ ":" + m1.getValue(), formatter2);
    						LocalDateTime endTime = LocalDateTime.parse("01/01/2021 " + h2.getValue() + ":" + m2.getValue(), formatter2);
    						if (tempTime.isAfter(startTime.minusMinutes(1)) && tempTime.isBefore(endTime.plusMinutes(1))) {
    							counter++;
    						}
    					}
    					if (counter<=0) {
    						filtered_list.remove(movie);
    						i--;
    					}
    				}

				}
    		}
    	}
		pages = filtered_list.size() / 3 + 1;
		if (pages>1)
			nextIcon.setVisible(true);
		prevIcon.setVisible(false);
		page = 1;
		resultsLabel.setText("There are " + filtered_list.size() + " matching results.");
		resultsLabel.setVisible(true);
		if (filtered_list.size()>3)
			nextIcon.setVisible(true);
		try {
			fillGrids(listGrid,type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChooseScreeningController.setImportedValues(branchCombo.getValue(), date1.getValue(), date2.getValue(), h1.getValue(), h2.getValue(), m1.getValue(), m2.getValue());
		
    }
    
    @FXML
    void resetFilters(ActionEvent event) {
		h1.setValue(null);
		h2.setValue(null);
		m1.setValue(null);
		m2.setValue(null);
		date1.setValue(null);
		date2.setValue(null);
		branchCombo.setValue(null);
		branchCombo.getEditor().setText("All");
		fieldText.setText("");
    }
    
	private void fillGrids(GridPane gridList, int listNum ) throws IOException {
		Platform.runLater(() -> {
			gridList.getChildren().clear();
				for (int j = 0 ; j < 3 ; j++) {
					Pair<Parent, Object> viewData = null;
					try {
						viewData = LayoutManager.getInstance().getFXML("displayMovieData");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					Node itemCell = viewData.getKey();
					DisplayMovieDataController controller = (DisplayMovieDataController) viewData.getValue();
					int index = (page - 1) * 3 + j;
					if (index >= filtered_list.size())
						break;
					Movie item = filtered_list.get(index);
					controller.setMovie(item);
					controller.setType(type);
					controller.setDisplay();
					gridList.add(itemCell, j, 0);
				}
			});
		waitAnchor.setVisible(false);

	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        listGrid.setVisible(false);
        prevIcon.setVisible(false);
    	nextIcon.setVisible(false);
    	date1.setDisable(true);
    	h1.setDisable(true);
    	m1.setDisable(true);
    	date2.setDisable(true);
    	h2.setDisable(true);
    	m2.setDisable(true);
    	branchCombo.setDisable(true);
    	resultsLabel.setVisible(false);
        branchCombo.getItems().addAll("All");
    	for (SirtyaBranch brnch : allBranches) {
			branchCombo.getItems().addAll(brnch.getAddress());
		}
		h1.getItems().addAll("");
		h2.getItems().addAll("");
		for (int i = 0; i < 10; i++) {
			h1.getItems().add("0" + Integer.toString(i));
			h2.getItems().add("0" + Integer.toString(i));
		}
		for (int i = 10; i < 24; i++) {
			h1.getItems().addAll(Integer.toString(i));
			h2.getItems().addAll(Integer.toString(i));
		}
		m1.getItems().addAll("","00","05");
		m2.getItems().addAll("","00","05");
		for (int i = 10; i < 60; i = i + 5) {
			m1.getItems().addAll(Integer.toString(i));
			m2.getItems().addAll(Integer.toString(i));
		}
		typeCombo.getItems().addAll("Cinema Screenings","On-Demand","Coming Soon");
    }
}
