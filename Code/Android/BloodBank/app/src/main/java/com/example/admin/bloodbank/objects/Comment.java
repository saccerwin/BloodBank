package com.example.admin.bloodbank.objects;

/**
 * Created by Admin on 20/01/2017.
 */

public class Comment {
    private String name;
    private String descriptionComment;

    public Comment(String name, String descriptionComment) {
        this.name = name;
        this.descriptionComment = descriptionComment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionComment() {
        return descriptionComment;
    }

    public void setDescriptionComment(String descriptionComment) {
        this.descriptionComment = descriptionComment;
    }
}
