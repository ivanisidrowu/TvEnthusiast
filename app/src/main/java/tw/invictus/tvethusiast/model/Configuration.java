package tw.invictus.tvethusiast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 9/21/15.
 */
public class Configuration {
    @SerializedName("images")
    @Expose
    private ImageParams images;
    @SerializedName("change_keys")
    @Expose
    private List<String> changeKeys = new ArrayList<String>();

    public ImageParams getImages() {
        return images;
    }

    public void setImages(ImageParams images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
}
