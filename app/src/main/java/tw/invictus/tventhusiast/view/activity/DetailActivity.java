package tw.invictus.tventhusiast.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.view.fragment.DetailFragment;

/**
 * Created by ivan on 12/27/15.
 */
public class DetailActivity extends AppCompatActivity{

    public static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            DetailFragment fragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
//            case R.id.settings:
//                startActivity(new Intent(this, SettingsActivity.class));
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
