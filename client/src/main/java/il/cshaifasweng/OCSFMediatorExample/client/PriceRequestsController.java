package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PriceRequestsController {
	ObservableList<Price> list =  FXCollections.observableArrayList();

//	private static List<Price> allPrices;
	private static Price temp;
//	public static List<Price> getAllPrices() {
//		return allPrices;
//	}
//
//	public static void setAllPrices(List<Price> list) {
//		PriceRequestsController.allPrices = list;
//	}

	@FXML
	private Button backButton;

	@FXML
	private TableView<Price> priceReqTable;

	@FXML
	private TableColumn<Price, String> movieNameCol;

	@FXML
	private TableColumn<Price, String> submittedbyCol;

	@FXML
	private TableColumn<Price, Double> oldPriceCol;

	@FXML
	private TableColumn<Price, Double> newPriceCol;

	@FXML
	private TableColumn<Price, String> dateCol;

	@FXML
	private TableColumn<Price, String> timeCol;

	@FXML
	private Button declineBtn;

	@FXML
	private Button acceptBtn;

	@FXML
	void AcceptReq(ActionEvent event) {
		Price request = new Price();
		request.setMovieID(temp.getMovieID());
		request.setNewPrice(temp.getNewPrice());
		request.setPriceId(temp.getID());
		try {
			SimpleClient.getClient().sendToServer(new Message("#changePrice",request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void DeclineReq(ActionEvent event) {
		Price request = new Price();
		request.setMovieID(temp.getMovieID());
		request.setNewPrice(temp.getNewPrice());
		request.setPriceId(temp.getID());
		try {
			SimpleClient.getClient().sendToServer(new Message("#deletePrice",request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void backToHome(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	 @FXML
	    void ExtractFromTable(MouseEvent event) {
		 temp = priceReqTable.getItems().get(priceReqTable.getSelectionModel().getSelectedIndex());
	    }
	
	

	@FXML
	void initialize() {
		EventBus.getDefault().register(this);
		movieNameCol.setCellValueFactory(new PropertyValueFactory<Price, String>("movieName"));
		newPriceCol.setCellValueFactory(new PropertyValueFactory<Price, Double>("newPrice"));
		oldPriceCol.setCellValueFactory(new PropertyValueFactory<Price, Double>("oldPrice"));
		submittedbyCol.setCellValueFactory(new PropertyValueFactory<Price, String>("workerName"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Price, String>("requestDate"));
		timeCol.setCellValueFactory(new PropertyValueFactory<Price, String>("requestTime"));
//		priceReqTable.setItems(list);
		
		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert priceReqTable != null
				: "fx:id=\"priceReqTable\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert movieNameCol != null
				: "fx:id=\"movieNameCol\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert submittedbyCol != null
				: "fx:id=\"submittedbyCol\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert oldPriceCol != null
				: "fx:id=\"oldPriceCol\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert newPriceCol != null
				: "fx:id=\"newPriceCol\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert timeCol != null : "fx:id=\"timeCol\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert declineBtn != null : "fx:id=\"declineBtn\" was not injected: check your FXML file 'priceRequests.fxml'.";
		assert acceptBtn != null : "fx:id=\"acceptBtn\" was not injected: check your FXML file 'priceRequests.fxml'.";

	}
	

	@SuppressWarnings("unchecked")
	@Subscribe
	public void onPriceReceivedEvent(PriceReceivedEvent event) {
//		EventBus.getDefault().register(this);
//		PriceRequestsController.setAllPrices((List<Price>) event.getPriceList());
		Platform.runLater(()->{
			list.clear();
			list.addAll((List<Price>)event.getPriceList());
			priceReqTable.getItems().clear();
			priceReqTable.getItems().addAll(list);
		});
	}
	
//	@Subscribe
//	public void onRefreshPriceRequest(RefreshPriceRequest event) {
//			list.clear();
//			list.addAll((List<Price>)event.getPricesList());
//			priceReqTable.getItems().clear();
//			priceReqTable.getItems().addAll(list);
//			initialize();
//	}
}
