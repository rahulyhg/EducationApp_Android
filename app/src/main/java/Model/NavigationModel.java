package Model;

import android.graphics.drawable.Drawable;

/**
 * Created by User on 1/9/2018.
 */

public class NavigationModel {

    private Drawable NavImage;
    private String navTitle;

    public NavigationModel(Drawable navImage, String navTitle) {
        NavImage = navImage;
        this.navTitle = navTitle;
    }

    public Drawable getNavImage() {
        return NavImage;
    }

    public void setNavImage(Drawable navImage) {
        NavImage = navImage;
    }

    public String getNavTitle() {
        return navTitle;
    }

    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle;
    }
}