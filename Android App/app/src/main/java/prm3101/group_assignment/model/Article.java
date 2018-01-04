package prm3101.group_assignment.model;

import java.io.Serializable;

/**
 * Created by ASUS on 23/10/2017.
 */

public class Article implements Serializable {
    private String thumnail;
    private String title;
    private String icon;
    private String date;
    private String url;

    public Article() {
    }

    public Article(String thumnail, String title, String icon, String date, String url) {
        this.thumnail = thumnail;
        this.title = title;
        this.icon = icon;
        this.date = date;
        this.url = url;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "thumnail='" + thumnail + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", date='" + date + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
