package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Imark on 9/7/2017.
 */

public class TextViewIcomoon extends TextView {
    public TextViewIcomoon(Context context) {
        super(context);
        setTypeface(context);
    }

    public TextViewIcomoon(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public TextViewIcomoon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }
    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "icomoon.ttf"));
    } 
}
