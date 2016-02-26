package fr.esilv.youtube.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.gson.Gson;
import fr.esilv.youtube.app.Data.Items;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Bundle extra = getIntent().getExtras();
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("json");
        Items obj = gson.fromJson(strObj, Items.class);
        ((TextView) findViewById(R.id.dtitre)).setText(obj.getSnippet().getTitle());
        ((TextView) findViewById(R.id.ddescription)).setText(obj.getSnippet().getDescription());
        ((TextView) findViewById(R.id.ddate)).setText(obj.getSnippet().getPublishedat());
        ((TextView) findViewById(R.id.dauthor)).setText(obj.getSnippet().getChanelTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
