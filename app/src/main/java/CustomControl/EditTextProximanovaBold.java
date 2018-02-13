package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextProximanovaBold extends EditText {

    public EditTextProximanovaBold(Context context) {
        super(context);
        setTypeface(context);

    }
    public EditTextProximanovaBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public EditTextProximanovaBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }
    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(),"PROXIMANOVA-BOLD.OTF"));
    }
}
