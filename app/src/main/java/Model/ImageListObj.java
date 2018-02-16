package Model;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by User on 2/16/2018.
 */

public class ImageListObj {
    ArrayList<String> uriArrayList;

    public ImageListObj(ArrayList<String> uriArrayList) {
        this.uriArrayList = uriArrayList;
    }

    public ArrayList<String> getUriArrayList() {
        return uriArrayList;
    }

    public void setUriArrayList(ArrayList<String> uriArrayList) {
        this.uriArrayList = uriArrayList;
    }
}
