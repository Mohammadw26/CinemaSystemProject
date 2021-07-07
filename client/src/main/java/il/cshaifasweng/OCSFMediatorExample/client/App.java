package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private SimpleClient client;

	@Override
	public void start(Stage stage) throws IOException {
		EventBus.getDefault().register(this);
		client = SimpleClient.getClient();
		client.openConnection();
		scene = new Scene(loadFXML("primary"));
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		EventBus.getDefault().unregister(this);
		super.stop();
	}

	@Subscribe
	public void onWarningEvent(WarningEvent event) {
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.INFORMATION, String.format("Message: %s\nTimestamp: %s\n",
					event.getWarning().getMessage(), event.getWarning().getTime().toString()));
			alert.show();
		});

	}

//    @SuppressWarnings("unchecked")
//	@Subscribe
//	public void onPriceReceivedEvent(PriceReceivedEvent event) {
//		PriceRequestsController.setAllPrices((List<Price>) event.getPriceList());
//
//		try {
//			App.setRoot("priceRequest");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	@SuppressWarnings("unchecked")
	@Subscribe
	public void onMovieReceivedEvent(MoviesReceivedEvent event) {
		DisplayListController.setMovieList((List<CinemaMovie>) event.getMovieCatalog());
		DisplayListController.setSoonList((List<ComingSoonMovie>) event.getMovieCatalog2());
		DisplayListController.setOnDemandList((List<OnDemandMovie>) event.getMovieCatalog3());
		DisplayListController.setAllBranches((List<SirtyaBranch>) event.getMovieCatalog4());
		try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Subscribe
	public void onBranchesReceivedEvent(BranchesReceivedEvent event) {
		EditMovieScreeningsController.setAllBranches((List<SirtyaBranch>) event.getBranchesList());
		try {
			App.setRoot("editMovieScreenings");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Subscribe
	public void onWorkersReceivedEvent(WorkersReceivedEvent event) {
		AdminPanelController.setAllWorkers((List<Worker>) event.getWorkersList());
		try {
			App.setRoot("adminPanel");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch();
	}

}