package fr.esilv.youtube.app;

/**
 * Created by louis on 26/02/2016.
 */
public class Video {
    String VideoId;
    String Title;
    String Thumbnail;

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getVideoId() {

        return VideoId;
    }

    public String getTitle() {
        return Title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public Video(String videoId, String title, String thumbnail) {

        VideoId = videoId;
        Title = title;
        Thumbnail = thumbnail;
    }
}
