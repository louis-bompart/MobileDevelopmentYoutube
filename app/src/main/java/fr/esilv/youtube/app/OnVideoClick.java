package fr.esilv.youtube.app;

import android.view.View;

/**
 * Created by louis on 12/02/2016.
 */
public class OnVideoClick implements View.OnClickListener {
    private Video clicked;

    public OnVideoClick(Video clicked) {
        this.clicked = clicked;
    }

    @Override
    public void onClick(View view) {

    }
}
