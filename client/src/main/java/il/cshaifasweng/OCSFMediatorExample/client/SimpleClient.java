package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;


public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String msgString = ((Message) msg).getMessage();
		if (msgString.startsWith("#SendMovies")) {
			EventBus.getDefault().post(new MoviesReceivedEvent((Message) msg));
		}
		else if (msgString.startsWith("#BranchesList")) {
			EventBus.getDefault().post(new BranchesReceivedEvent((Message) msg));
		}
		else if (msgString.startsWith("#RefreshAdd")) {
			EditMovieScreeningsController.setMovie((Movie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#RefreshEdit")) {
			EditMovieScreeningsController.setMovie((Movie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#RefreshDelete")) {
			EditMovieScreeningsController.setMovie((Movie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			
			
//		if (msg.getClass().equals(MovieList.class)) {
//			EventBus.getDefault().register(this);
//			EventBus.getDefault().post(new MoviesReceivedEvent((MovieList) msg));
//		}
//	}

	
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
