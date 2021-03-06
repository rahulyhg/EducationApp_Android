package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewProximanovaBold extends TextView {
    public TextViewProximanovaBold(Context context) {
        super(context);
        setTypeface(context);

    }
    public TextViewProximanovaBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public TextViewProximanovaBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }
    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(),"PROXIMANOVA-BOLD.OTF"));
    }
}
