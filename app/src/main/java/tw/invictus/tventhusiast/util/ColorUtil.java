package tw.invictus.tventhusiast.util;

import android.graphics.Color;
import android.support.v7.graphics.Palette;

/**
 * Created by ivan on 2/14/16.
 */
public class ColorUtil {

    public static boolean isColorCloserToWhite(int rgb){
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb >> 0) & 0xFF;

        double y = 0.2126 * r + 0.7152 * g + 0.0722 * b;

        if (y < 128){
            return false;
        }else{
            return true;
        }
    }

    public static int getDarkColorFromPalette(Palette palette){
        if(palette.getDarkVibrantSwatch() != null){
            return palette.getDarkVibrantSwatch().getRgb();
        }else if(palette.getDarkMutedSwatch() != null){
            return palette.getDarkMutedSwatch().getRgb();
        }else if(palette.getMutedSwatch() != null){
            return palette.getMutedSwatch().getRgb();
        }

        return Color.DKGRAY;
    }

    public static int getLightColorFromPalette(Palette palette){
        if(palette.getVibrantSwatch() != null){
            return palette.getVibrantSwatch().getRgb();
        }else if(palette.getLightVibrantSwatch() != null){
            return palette.getLightVibrantSwatch().getRgb();
        }else if(palette.getLightMutedSwatch() != null){
            return palette.getLightMutedSwatch().getRgb();
        }

        return Color.GRAY;
    }

    public static int getBackgroundColorFromPalette(Palette palette){
        if(palette.getDarkMutedSwatch() != null){
            return palette.getDarkMutedSwatch().getRgb();
        }else if(palette.getMutedSwatch() != null){
            return palette.getMutedSwatch().getRgb();
        }else if(palette.getDarkVibrantSwatch() != null){
            return palette.getDarkVibrantSwatch().getRgb();
        }

        return Color.DKGRAY;
    }
}
