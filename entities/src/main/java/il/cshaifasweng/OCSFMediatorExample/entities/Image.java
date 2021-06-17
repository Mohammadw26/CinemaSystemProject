package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "images")

public class Image implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "Image_name")
	private String name;
    private String imgURL;
    
    
    
    public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "image")
    private Movie movie;
    
    public Image() {}
    
    public Image(String name,String imgURL) {
        this.name = name;
		this.imgURL = imgURL;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getId() {
		return id;
	}
}
