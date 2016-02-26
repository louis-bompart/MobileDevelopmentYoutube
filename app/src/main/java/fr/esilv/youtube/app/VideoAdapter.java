package fr.esilv.youtube.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by louis on 12/02/2016.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    private Videos videos;

    public VideoAdapter(Videos videos) {
        this.videos = videos;
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_view, parent, false);
        // set the view's size, margins, padding and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.titre.setText(videos.get(i).getSnippet().getTitle());
        LoadImageFromURL loadImageFromURL = new LoadImageFromURL(viewHolder.image);
        loadImageFromURL.execute(videos.get(i).getSnippet().getThumbnail().getMedium().getUrl());
        viewHolder.itemView.setOnClickListener(new OnVideoClick(videos.get(i)));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titre;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            titre = (TextView) itemView.findViewById(R.id.titre);
            image = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public class LoadImageFromURL extends AsyncTask<String,Void,Bitmap>
    {
        private ImageView view;

        public LoadImageFromURL(ImageView view) {
            this.view = view;
        }

        @Override
        protected Bitmap doInBackground (String...params){
        // TODO Auto-generated method stub

        try {
            URL url = new URL(params[0]);
            InputStream is = url.openConnection().getInputStream();
            Bitmap bitMap = BitmapFactory.decodeStream(is);
            return bitMap;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

        @Override
        protected void onPostExecute (Bitmap result){
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        view.setImageBitmap(result);
    }
    }
}
