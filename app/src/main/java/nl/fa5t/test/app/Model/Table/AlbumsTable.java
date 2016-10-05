package nl.fa5t.test.app.Model.Table;

import nl.fa5t.test.app.Model.Entity.Album;

/**
 * Created by ian on 5-10-16.
 */
public class AlbumsTable extends Table<Album> {
    public AlbumsTable() {
        super();
        singular = "album";
    }
}
