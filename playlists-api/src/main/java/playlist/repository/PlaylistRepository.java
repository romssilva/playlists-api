package playlist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import playlist.model.Playlist;

public interface PlaylistRepository extends PagingAndSortingRepository<Playlist, Long> {}