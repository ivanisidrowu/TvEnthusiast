package tw.invictus.tventhusiast.view.event;

/**
 * Created by ivan on 11/21/15.
 */
public class GetPaletteEvent {
    private int darkColor;
    private int lightColor;
    private int backgroundColor;

    public GetPaletteEvent(int darkColor, int lightColor, int backgroundColor) {
        this.darkColor = darkColor;
        this.lightColor = lightColor;
        this.backgroundColor = backgroundColor;
    }

    public int getDarkColor() {
        return darkColor;
    }

    public void setDarkColor(int darkColor) {
        this.darkColor = darkColor;
    }

    public int getLightColor() {
        return lightColor;
    }

    public void setLightColor(int lightColor) {
        this.lightColor = lightColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
