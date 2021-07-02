package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;

public class RefreshCatalogEvent {
	private  List<CinemaMovie> moviesList;
	private  List<OnDemandMovie> moviesListDemand;
	private  List<ComingSoonMovie> soonMovieList;
	public List<CinemaMovie> getMoviesList() {
		return moviesList;
	}
	public void setMoviesList(List<CinemaMovie> moviesList) {
		this.moviesList = moviesList;
	}
	public List<ComingSoonMovie> getSoonMovieList() {
		return soonMovieList;
	}
	public void setSoonMovieList(List<ComingSoonMovie> soonMovieList) {
		this.soonMovieList = soonMovieList;
	}
	public List<OnDemandMovie> getMoviesListDemand() {
		return moviesListDemand;
	}
	public void setMoviesListDemand(List<OnDemandMovie> moviesListDemand) {
		this.moviesListDemand = moviesListDemand;
	}
	
	public RefreshCatalogEvent(Message msg) {
		moviesList = (List<CinemaMovie>)msg.getObject();
		moviesListDemand = (List<OnDemandMovie>)msg.getObject2();
		soonMovieList = ( List<ComingSoonMovie>)msg.getObject3();
	}
	
	public void sendRefreshRequest() {
		Message msg = new Message("#getAllMovies");
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
