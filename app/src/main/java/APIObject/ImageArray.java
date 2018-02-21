package APIObject;

import java.util.ArrayList;

/**
 * Created by User on 2/21/2018.
 */

public class ImageArray {
    ArrayList<String> imagesPath;

    public ImageArray(ArrayList<String> imagesPath) {
        this.imagesPath = imagesPath;
    }

    public ArrayList<String> getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(ArrayList<String> imagesPath) {
        this.imagesPath = imagesPath;
    }
}
