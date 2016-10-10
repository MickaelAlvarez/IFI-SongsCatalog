package fil.iagl.ifi.songscatalog.service;

import fil.iagl.ifi.songscatalog.model.Song;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("songService")
@Transactional
public class SongService implements SongServiceInterface {
    private static final AtomicLong counter = new AtomicLong();
    private static List<Song> songs;

    static {
        songs = new ArrayList<>();
        songs.add(new Song(counter.incrementAndGet(), "Highway to Hell", "Highway to Hell", "AC/DC"));
        songs.add(new Song(counter.incrementAndGet(), "Bohemian Rhapsody", "A Night at the Opera", "Queen"));
        songs.add(new Song(counter.incrementAndGet(), "Hysteria", "Absolution", "Muse"));
    }

    @Override
    public List<Song> findAllSongs() {
        return songs;
    }

    @Override
    public Song findById(long id) {
        for (Song song: songs) {
            if (song.getId() == id) return song;
        }
        return null;
    }

	@Override
	public List<Song> deleteSong(long id) {
		Song toDelete = null;
		
		for(Song song : songs) {
			if(song.getId() == id) {
				toDelete = song;
				break;
			}
		}
		
		if(toDelete != null) {
			songs.remove(toDelete);
		}
		
		return findAllSongs();
	}

	@Override
	public List<Song> addSong(String name, String album, String singer) {
		songs.add(new Song(counter.incrementAndGet(), name, album, singer));
		return findAllSongs();
	}

	@Override
	public List<Song> addOrReplaceSong(long id, String name, String album, String singer) {
		Song existantSong = null;
		
		for(Song song : songs) {
			if(song.getId() == id) {
				existantSong = song;
				break;
			}
		}
		
		Song song = new Song(id, name, album, singer);
		
		if(existantSong != null) {
			songs.set(songs.indexOf(existantSong), song);
		} else {
			songs.add(song);
		}
		
		return findAllSongs();
	}
	
	
}
