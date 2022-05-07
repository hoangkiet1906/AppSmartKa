package com.rajendra.onlinedailygroceries.model;

public class Blog {
    int idblog;
    String title;
    String author;
    String content;
    String date;
    String image;
    String tag;
    int status;

    public Blog(int idblog, String title, String author, String content, String date, String image, String tag, int status) {
        this.idblog = idblog;
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
        this.image = image;
        this.tag = tag;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "idblog=" + idblog +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                ", tag='" + tag + '\'' +
                ", status=" + status +
                '}';
    }

    public int getIdblog() {
        return idblog;
    }

    public void setIdblog(int idblog) {
        this.idblog = idblog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
