package com.example.admin.bloodbank.objects;

/**
 * Created by Admin on 10/01/2017.
 */

public class PostNews {
    public String nameClub;
    public String timePost;
    public String description;

    public PostNews() {
    }

    public PostNews(String nameClub, String timePost, String description) {
        this.nameClub = nameClub;
        this.timePost = timePost;
        this.description = description;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
