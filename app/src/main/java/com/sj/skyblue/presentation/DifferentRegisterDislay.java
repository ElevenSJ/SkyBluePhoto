package com.sj.skyblue.presentation;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;

import com.sj.skyblue.R;

/**
 * Created by Sunj on 2018/11/16.
 */

public class DifferentRegisterDislay extends Presentation {
    public DifferentRegisterDislay(Context outerContext, Display display) {
        super(outerContext,display);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_register);
    }
}
