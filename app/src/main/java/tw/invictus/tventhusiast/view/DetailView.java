package tw.invictus.tventhusiast.view;

import java.util.List;

import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.model.pojo.Video;

/**
 * Created by ivan on 12/27/15.
 */
public interface DetailView {

    void onVideosLoaded(List<Video> videos);

    void onTvShowLoaded(TvShow show);

    void onTvShowAdded();

    void onTvShowDeleted();

}
