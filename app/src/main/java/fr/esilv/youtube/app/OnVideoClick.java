package fr.esilv.youtube.app;

import android.content.Intent;
import android.view.View;
import com.google.gson.Gson;
import fr.esilv.youtube.app.Data.Items;

/**
 * Created by louis on 12/02/2016.
 */
public class OnVideoClick implements View.OnClickListener {
    private Items clicked;

    public OnVideoClick(Items clicked) {
        this.clicked = clicked;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(),VideoActivity.class);
        intent.putExtra("json",new Gson().toJson(clicked));
        view.getContext().startActivity(intent);
    }
}
