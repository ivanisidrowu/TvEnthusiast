package tw.invictus.tvethusiast.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import tw.invictus.tvethusiast.model.TvShow;

/**
 * Created by ivan on 10/23/15.
 */
public class SeriesResponse {

    @SerializedName("results")
    @Expose
    private List<TvShow> results = new ArrayList<TvShow>();

    public List<TvShow> getResult() {
        return results;
    }

    public void setResult(List<TvShow> result) {
        this.results = result;
    }
}
