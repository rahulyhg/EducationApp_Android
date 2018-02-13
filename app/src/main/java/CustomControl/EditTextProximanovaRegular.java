package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextProximanovaRegular extends EditText {

    public EditTextProximanovaRegular(Context context) {
        super(context);
        setTypeface(context);

    }

    public EditTextProximanovaRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public EditTextProximanovaRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "PROXIMANOVASOFT-REGULAR.OTF"));
    }
}
