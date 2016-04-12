package tw.invictus.tventhusiast.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.pojo.Genre;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.event.RefreshMyTvShowEvent;

/**
 * Created by ivan on 10/23/15.
 */
public class OverviewFragment extends Fragment {

    private TvShow show;

    @Bind(R.id.text)
    TextView textView;

    @Bind(R.id.genre)
    TextView genre;

    @Bind(R.id.duration)
    TextView duration;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(
                R.layout.fragment_overview, container, false);
        EventBus.getDefault().registerSticky(this);
        ButterKnife.bind(this, nestedScrollView);
        textView.setText(getOverviewText());
        String genreMsg = getString(R.string.genre, getGenres());
        genre.setText(genreMsg);
        duration.setText(getDurationMsg());
        return nestedScrollView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    private String getOverviewText(){
        String result;
        if(show.getOverview()!= null && show.getOverview().length() > 0){
            result = show.getOverview();
        }else{
            result = getString(R.string.no_translation);
        }
        return result;
    }

    public TvShow getShow() {
        return show;
    }

    public void setShow(TvShow show) {
        this.show = show;
    }

    private String getGenres() {
        String result = "";
        for (Genre genre : show.getGenres()) {
            if (result.length() != 0) {
                result = result + ", " + genre.getName();
            } else {
                result = genre.getName();
            }
        }

        return result;
    }

    private String getDurationMsg() {
        String firstDate = show.getFirstAirDate().split("-")[0];
        String lastDate = show.getLastAirDate().split("-")[0];

        return getString(R.string.duration, show.getTitle(), firstDate, lastDate);
    }

    @SuppressWarnings("unused")
    public void onEvent(RefreshMyTvShowEvent event) {
        this.show = event.getShow();
        // TODO
    }
}
