package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

public class ShowCatalogController {
	public static int indx = 0;
	public static List<Movie> catalog;
	
	
	public static List<Movie> getCatalog() {
		return catalog;
	}

	public static void setCatalog(List<Movie> catalog) {
		ShowCatalogController.catalog = catalog;
	}

	public ShowCatalogController() {}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listToTable"
    private TableView<Movie> listToTable; // Value injected by FXMLLoader

    @FXML // fx:id="posterCol"
    private TableColumn<Movie, String> posterCol; // Value injected by FXMLLoader

    @FXML // fx:id="titleCol"
    private TableColumn<Movie, String> titleCol; // Value injected by FXMLLoader

    @FXML // fx:id="actorsCol"
    private TableColumn<Movie, String> actorsCol; // Value injected by FXMLLoader

    @FXML // fx:id="productionCol"
    private TableColumn<Movie, String> productionCol; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionCol"
    private TableColumn<Movie, String> descriptionCol; // Value injected by FXMLLoader

    @FXML // fx:id="costCol"
    private TableColumn<Movie, Integer> costCol; // Value injected by FXMLLoader

    @FXML // fx:id="timesCol"
    private TableColumn<Movie, String> timesCol; // Value injected by FXMLLoader

    @FXML // fx:id="homeBtn"
    private Button homeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="EditBtn"
    private Button EditBtn; // Value injected by FXMLLoader
    
    ObservableList<Movie> list = FXCollections.observableArrayList(catalog);
    	

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	posterCol.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieTitle"));
    	titleCol.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieTitle"));
    	actorsCol.setCellValueFactory(new PropertyValueFactory<Movie,String>("starringActors"));
    	productionCol.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieProducer"));
    	descriptionCol.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieDescription"));
    	costCol.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("ticketCost"));
    	timesCol.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieTitle"));
    	listToTable.setItems(list);
    }
}


