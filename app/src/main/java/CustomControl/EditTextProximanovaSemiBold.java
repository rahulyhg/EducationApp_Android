package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;


public class EditTextProximanovaSemiBold extends EditText {

    public EditTextProximanovaSemiBold(Context context) {
        super(context);
        setTypeface(context);

    }

    public EditTextProximanovaSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public EditTextProximanovaSemiBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "PROXIMANOVA-SEMIBOLD.OTF"));
    }
}
