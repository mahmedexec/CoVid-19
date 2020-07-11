package com.ahmedtan.underground;

public class Safety {
    private int resString;
    private int resImage;

    public Safety(int resString, int resImage) {
        this.resString = resString;
        this.resImage = resImage;
    }

    public int getResString() {
        return resString;
    }

    public void setResString(int resString) {
        this.resString = resString;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }
}
