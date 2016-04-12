package tw.invictus.tventhusiast.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ivan on 10/23/15.
 */
public class TvShowsResponse {

    @SerializedName("results")
    @Expose
    private ArrayList<TvShow> results = new ArrayList<TvShow>();

    public ArrayList<TvShow> getResult() {
        return results;
    }

    public void setResult(ArrayList<TvShow> result) {
        this.results = result;
    }
}
