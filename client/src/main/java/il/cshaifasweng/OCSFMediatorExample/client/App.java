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

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;

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
    	
		try {
			App.setRoot("ShowCatalog");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	Platform.runLater(() -> {
//    		Alert alert = new Alert(AlertType.WARNING,
//        			String.format("Message: %s\nTimestamp: %s\n",
//        					event.getWarning().getMessage(),
//        					event.getWarning().getTime().toString())
//        	);
//        	alert.show();
//    	});
    }
    
    @SuppressWarnings("unchecked")
	@Subscribe
    public void onMovieReceivedEvent(MoviesReceivedEvent event) {
    	ShowCatalogController.setCatalog((List<Movie>) event.getMovieCatalog());
    	DisplayListController.setMovieList((List<Movie>) event.getMovieCatalog());
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

	public static void main(String[] args) {
        launch();
    }

}