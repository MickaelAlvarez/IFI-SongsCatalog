package fil.iagl.ifi.songscatalog.service;

import fil.iagl.ifi.songscatalog.model.Song;

import java.util.List;

/**
 * Service.
 *
 * TODO: add all required methods for a complete service.
 */
public interface SongServiceInterface {
    List<Song> findAllSongs();
    Song findById(long id);
    List<Song> deleteSong(long id);
    List<Song> addSong(String name, String album, String singer);
    List<Song> addOrReplaceSong(long id, String name, String album, String singer);
}
