package playlist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import playlist.model.Playlist;
import playlist.repository.PlaylistRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository playlistRepository;
	
	public Page<Playlist> getAllPlaylists(int pageNumber, int pageSize) {
		return playlistRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}
	
	public Optional<Playlist> getPlaylistById(Long id) {
		return playlistRepository.findById(id);
	}
	
	public Playlist createPlaylist(Playlist playlist) {
		playlist.setRating(0.0);
		return playlistRepository.save(playlist);
	}
	
	public Playlist updatePlaylist(Long id, Playlist playlist) {
    	Optional<Playlist> playlistOptional = playlistRepository.findById(id);
    	
    	if (!playlistOptional.isPresent()) {
    		playlist.setId(id);
    		return playlistRepository.save(playlist);
    	}
    	
    	Playlist existingPlaylist = playlistOptional.get();
    	if (!playlist.getName().isEmpty()) existingPlaylist.setName(playlist.getName());
    	if (!playlist.getSongsIds().isEmpty()) existingPlaylist.setSongsIds(playlist.getSongsIds());
    	if (playlist.getRating() != null) existingPlaylist.setRating(playlist.getRating());
		return playlistRepository.save(playlist);
	}
	
	public void deletePlaylist(Long id) {
		playlistRepository.deleteById(id);
	}
}
