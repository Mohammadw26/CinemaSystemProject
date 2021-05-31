package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

public class MoviesReceivedEvent {
	private Object movieCatalog;

	public MoviesReceivedEvent(Message msg) {
		this.movieCatalog = msg.getObject();
	}
	
	public Object getMovieCatalog() {
		return movieCatalog;
	}
	
}
