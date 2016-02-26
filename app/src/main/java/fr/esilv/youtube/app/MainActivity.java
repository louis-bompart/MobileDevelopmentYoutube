package fr.esilv.youtube.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.esilv.youtube.app.Data.Items;
import fr.esilv.youtube.app.Data.Youtube_json;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private VideoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void setRecyclerView() {
        recyclerview = (RecyclerView) findViewById(R.id.videos_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new VideoAdapter(new Videos());
        recyclerview.setAdapter(adapter);
    }

    private void doRequest(String request)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q="+request+"&key="+getString(R.string.YoutubeKey);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseVideos(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(stringRequest);
    }

    private void parseVideos(String response) {
        Gson gson = new Gson();
        Youtube_json json = gson.fromJson(response, Youtube_json.class);

        //populate the View
        if (json != null) {
            final Videos videos = new Videos();
            videos.addAll(json.getItems());
            adapter.setVideos(videos);
            adapter.notifyDataSetChanged();
        }
    }

    public void doSearch(View view) {
        EditText editText = (EditText)findViewById(R.id.keywords);
        String request = editText.getText().toString();
        doRequest(request);
    }
}
