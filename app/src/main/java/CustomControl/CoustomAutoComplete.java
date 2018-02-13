package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;

/**
 * Created by User on 10/4/2017.
 */

public class CoustomAutoComplete extends AppCompatAutoCompleteTextView {
    public CoustomAutoComplete(Context context) {
        super(context);
        setTypeface(context);
    }

    public CoustomAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public CoustomAutoComplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }
    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "PROXIMANOVASOFT-REGULAR.OTF"));
    }

}
