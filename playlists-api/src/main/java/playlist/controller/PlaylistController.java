package playlist.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import playlist.model.Playlist;
import playlist.repository.PlaylistRepository;

@RestController
public class PlaylistController {

	private final String API_VERSION = "api/v1";
    
    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping(API_VERSION + "/playlists")
    public Page<Playlist> getAllPlaylists(
    		@RequestParam(value="pageNumber", required=false, defaultValue="0") int pageNumber,
    		@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize
		) {
		return playlistRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    
    @GetMapping(API_VERSION + "/playlists/{id}")
    public Playlist getPlaylist(HttpServletResponse response, @PathVariable Long id) {
    	Optional<Playlist> playlistOptional = playlistRepository.findById(id);
    	if (playlistOptional.isPresent()) {
    		return playlistOptional.get();
    	} else {
    		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return null;
    	}
    }
    
    @PostMapping(API_VERSION + "/playlists")
    public Playlist createPlaylist(HttpServletResponse response, @RequestBody Playlist newPlaylist) {
    	Playlist playlist = playlistRepository.save(newPlaylist);
    	response.setStatus(HttpServletResponse.SC_CREATED);
    	return playlist;
    }
    
    @PutMapping(API_VERSION + "/playlists/{id}")
    public Playlist updatePlaylist(HttpServletResponse response, @PathVariable Long id, @RequestBody Playlist playlist) {
    	Optional<Playlist> playlistOptional = playlistRepository.findById(id);
    	
    	if (!playlistOptional.isPresent()) {
    		response.setStatus(HttpServletResponse.SC_CREATED);
    		playlist.setId(id);
    		return playlistRepository.save(playlist);
    	}
    	
    	Playlist existingPlaylist = playlistOptional.get();
    	if (!playlist.getName().isEmpty()) existingPlaylist.setName(playlist.getName());
    	if (!playlist.getSongsIds().isEmpty()) existingPlaylist.setSongsIds(playlist.getSongsIds());
    	if (playlist.getRating() != null) existingPlaylist.setName(playlist.getName());
    	
    	return playlistRepository.save(existingPlaylist);
    }
    
    @DeleteMapping(API_VERSION + "/playlists/{id}")
    public void deletePlaylist(HttpServletResponse response, @PathVariable Long id) {
    	playlistRepository.deleteById(id);
    }
}
