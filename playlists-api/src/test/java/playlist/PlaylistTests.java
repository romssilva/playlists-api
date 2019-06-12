/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package playlist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import playlist.model.Playlist;
import playlist.repository.PlaylistRepository;
import playlist.service.PlaylistService;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistTests {
	
	@InjectMocks
	private PlaylistService playlistService;
	
	@Mock
	private PlaylistRepository playlistRepository;
	
	@Captor
	private ArgumentCaptor<Playlist> playlistCaptor;
    
    @Test
    public void savePlaylist() {
    	Playlist playlist = new Playlist(null, "Testing Playlist", null, 5.0);
    	playlistService.createPlaylist(playlist);
    	Mockito.verify(playlistRepository).save(playlistCaptor.capture());
    	Playlist resultPlaylist = playlistCaptor.getValue();

    	assertThat(resultPlaylist.getName()).isEqualTo(playlist.getName());
    	assertThat(resultPlaylist.getRating()).isEqualTo(playlist.getRating());
    }
}
