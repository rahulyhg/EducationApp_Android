package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonProximanovaSemiBold extends Button {
    public ButtonProximanovaSemiBold(Context context) {
        super(context);
        setTypeface(context);

    }

    public ButtonProximanovaSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public ButtonProximanovaSemiBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "PROXIMANOVA-SEMIBOLD.OTF"));
    }
}
