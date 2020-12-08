package com.kltong;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2020/11/30 15:22
 */
public class DownloadCheckFileForJsonParam {

    private String date;

    @Override
    public String toString() {
        return "DownloadCheckFileForJsonParam{" +
                "date='" + date + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public DownloadCheckFileForJsonParam setDate(String date) {
        this.date = date;
        return this;
    }
}
