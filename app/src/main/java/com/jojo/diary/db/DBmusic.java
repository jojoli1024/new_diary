package com.jojo.diary.db;

public class DBmusic {
    private int musicId;
    private String musicName;

    public DBmusic(){}

    public DBmusic(int musicId,String musicName){
        this.musicId=musicId;
        this.musicName=musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getMusicId() {
        return musicId;
    }

    public String getMusicName() {
        return musicName;
    }
}
