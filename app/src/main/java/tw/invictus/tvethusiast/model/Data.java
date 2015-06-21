package tw.invictus.tvethusiast.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ivan on 6/21/15.
 */
@Root(name = "Data")
public class Data {

    @ElementList(inline = true)
    private List<Series> seriesList;

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }
}
