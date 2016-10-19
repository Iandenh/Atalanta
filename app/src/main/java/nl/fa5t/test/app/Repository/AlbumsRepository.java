package nl.fa5t.test.app.Repository;

import nl.fa5t.test.app.Model.Entity.Album;

/**
 * Created by ian on 5-10-16.
 */
public class AlbumsRepository extends Repository<Album> {
    public AlbumsRepository() {
        super();
        model = Album.class;
        singular = "album";
    }
}
