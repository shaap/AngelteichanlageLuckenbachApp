package com.shaap.proevents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/**
 * Created by Sebastian on 25.05.2014.
 */
public class MapPin extends CompoundButton {
    public MapPin(Context context) {
        super(context);
    }

    public MapPin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapPin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setChecked(boolean checked) {

        super.setChecked(checked);
    }
}
