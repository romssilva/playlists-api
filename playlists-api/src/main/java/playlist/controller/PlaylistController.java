package playlist.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import playlist.model.Playlist;
import playlist.service.PlaylistService;

@RestController
public class PlaylistController {

	private final String API_VERSION = "api/v1";
    
    @Autowired
    private PlaylistService playlistService;

    @GetMapping(API_VERSION + "/playlists")
    public ResponseEntity<Page<Playlist>> getAllPlaylists(
    		@RequestParam(value="pageNumber", required=false, defaultValue="0") int pageNumber,
    		@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize
		) {
		return ResponseEntity.ok(playlistService.getAllPlaylists(pageNumber, pageSize));
    }
    
    @GetMapping(API_VERSION + "/playlists/{id}")
    public ResponseEntity<Optional<Playlist>> getPlaylist(HttpServletResponse response, @PathVariable Long id) {
    	return Optional.ofNullable(playlistService.getPlaylistById(id))
    			.map(playlist -> ResponseEntity.ok(playlist))
    			.orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping(API_VERSION + "/playlists")
    public ResponseEntity<Object> createPlaylist(HttpServletResponse response, @RequestBody Playlist playlist) {
    	Long newPlaylistId = playlistService.createPlaylist(playlist).getId();
    	return ResponseEntity.created(URI.create(API_VERSION + "/playlists/" + newPlaylistId)).build();
    }
    
    @PutMapping(API_VERSION + "/playlists/{id}")
    public ResponseEntity<Playlist> updatePlaylist(HttpServletResponse response, @PathVariable Long id, @RequestBody Playlist playlist) {
    	return ResponseEntity.ok(playlistService.updatePlaylist(id, playlist));
    }
    
    @DeleteMapping(API_VERSION + "/playlists/{id}")
    public ResponseEntity<Object> deletePlaylist(HttpServletResponse response, @PathVariable Long id) {
    	playlistService.deletePlaylist(id);
    	return ResponseEntity.ok().build();
    }
}
