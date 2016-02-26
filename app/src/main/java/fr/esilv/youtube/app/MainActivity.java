package fr.esilv.youtube.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Videos mockData;
    private RecyclerView recyclerview;
    private VideoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mockData = new Videos();
        mockData.add(new Video("W2fIb_l_x9I","La rentrée en école d'ingénieurs post-bac , ESILV promotion 2018 !","https://i.ytimg.com/vi/W2fIb_l_x9I/default.jpg"));
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
        adapter = new VideoAdapter(mockData);
        recyclerview.setAdapter(adapter);
    }
}
