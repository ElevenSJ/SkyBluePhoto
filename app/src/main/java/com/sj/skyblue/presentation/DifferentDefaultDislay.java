package com.sj.skyblue.presentation;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;

import com.sj.skyblue.R;

/**
 * Created by Sunj on 2018/11/16.
 */

public class DifferentDefaultDislay extends Presentation {
    public DifferentDefaultDislay(Context outerContext, Display display) {
        super(outerContext,display);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_default);
    }
}
