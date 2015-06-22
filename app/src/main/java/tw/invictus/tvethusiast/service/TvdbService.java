package tw.invictus.tvethusiast.service;

/**
 * Created by ivan on 6/21/15.
 */
public class TvdbService {

    private static TvdbService instance = null;

    private TvdbService(){

    }

    public static TvdbService getInstance() {
        if (instance == null) {
            synchronized (TvdbService.class) {
                if (instance == null) {
                    instance = new TvdbService();
                }
            }
        }
        return instance;
    }

}
