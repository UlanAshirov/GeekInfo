package com.joma.geekinfo.chat;

public class ChatModel {
    private String name;
    private int imageResource;

    public ChatModel(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
