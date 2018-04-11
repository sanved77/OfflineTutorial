package com.sanved.offlinetutorial;

/**
 * Created by Sanved on 12-04-2018.
 */

public class DataKaRakhwala {

    String title, filename;
    int pageno;

    DataKaRakhwala(String title, int pageno, String filename){
        this.title = title;
        this.pageno = pageno;
        this.filename = filename;
    }

    public int getPageno() {
        return pageno;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }
}
