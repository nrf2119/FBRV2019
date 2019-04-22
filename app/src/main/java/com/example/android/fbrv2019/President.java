package com.example.android.fbrv2019;

public class President {

    private String name;
    private String info;
    private int photoResId;

   // com.google.firebase.database.DatabaseException: Class com.example.android.fbrv2019.President does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped.
    public President() {
    }

    //not used here
    public President(String name, String info, int photoResId) {
        this.name = name;
        this.info = info;
        this.photoResId = photoResId;
    }

    public President(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public int getPhotoId() {
        return photoResId;
    }
}


