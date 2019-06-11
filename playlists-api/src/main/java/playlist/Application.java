package playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import playlist.repository.PlaylistRepository;

@SpringBootApplication
public class Application {
	
	@Autowired
	PlaylistRepository playlistRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
