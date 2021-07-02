package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class ComingSoonMovie extends Movie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5682785014595922507L;
	
	ComingSoonMovie(){}
	
	public ComingSoonMovie (String title,String titleHeb, String producer, String actors, String description, Image image) {
		this.movieTitle = title;
		this.movieTitleHeb = titleHeb;
		this.movieProducer = producer;
		this.starringActors = actors;
		this.movieDescription = description;
		this.setImage(image);
		this.sirtyaBranch = new ArrayList<SirtyaBranch>();
	}
	public ComingSoonMovie (int id, String title,String titleHeb, String producer, String actors, String description, Image image) {
		this.id = id;
		this.movieTitle = title;
		this.movieTitleHeb = titleHeb;
		this.movieProducer = producer;
		this.starringActors = actors;
		this.movieDescription = description;
		this.setImage(image);
		this.sirtyaBranch = new ArrayList<SirtyaBranch>();
	}
}
