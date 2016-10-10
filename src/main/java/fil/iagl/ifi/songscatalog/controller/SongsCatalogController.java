package fil.iagl.ifi.songscatalog.controller;

import java.util.List;

import fil.iagl.ifi.songscatalog.model.Song;
import fil.iagl.ifi.songscatalog.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle requests for producing JSON response.
 *
 * TODO: to be completed.
 */
@RestController
public class SongsCatalogController {

    // Inject data service.
    @Autowired
    SongService songService;

    @RequestMapping(value = "/song/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> listAllUSongs() {
        List<Song> songs = songService.findAllSongs();
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @RequestMapping(value = "/song/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Song> getSong(@PathVariable("id") long id) {
        Song song = songService.findById(id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/song/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> deleteSong(@PathVariable("id") long id) {
        List<Song> songs = songService.deleteSong(id);
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/song/{name}/{album}/{singer}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> addSong(@PathVariable("name") String name, @PathVariable("album") String album, @PathVariable("singer") String singer) {
        List<Song> songs = songService.addSong(name, album, singer);
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/song/{id}/{name}/{album}/{singer}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> addOrReplaceSong(@PathVariable("id") long id, @PathVariable("name") String name, @PathVariable("album") String album, @PathVariable("singer") String singer) {
        List<Song> songs = songService.addOrReplaceSong(id, name, album, singer);
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
