package playlist.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "playlists")
public class Playlist {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String name;
	private ArrayList<String> songsIds;
    private Integer rating;
    
    public Playlist() {}

    public Playlist(Long id, String name, ArrayList<String> songsIds, Integer rating) {
    	this.id = id;
    	this.name = name;
    	this.songsIds = songsIds;
    	this.rating = rating;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getSongsIds() {
		return songsIds;
	}

	public void setSongsIds(ArrayList<String> songsIds) {
		this.songsIds = songsIds;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
