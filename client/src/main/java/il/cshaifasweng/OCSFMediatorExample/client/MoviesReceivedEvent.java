package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

public class MoviesReceivedEvent {
	private Object movieCatalog;
	private Object movieCatalog2;
	private Object movieCatalog3;

	public MoviesReceivedEvent(Message msg) {
		this.movieCatalog = msg.getObject();
		this.movieCatalog2 = msg.getObject2();
		this.movieCatalog3 = msg.getObject3();
	}
	
	public Object getMovieCatalog() {
		return movieCatalog;
	}
	
	
	public Object getMovieCatalog2() {
		return movieCatalog2;
	}
	
	public Object getMovieCatalog3() {
		return movieCatalog3;
	}
	
}
