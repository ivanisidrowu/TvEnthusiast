package tw.invictus.tventhusiast.model.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan on 2/28/16.
 */
public class RealmGenre extends RealmObject{

    private String name;
    @PrimaryKey
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
