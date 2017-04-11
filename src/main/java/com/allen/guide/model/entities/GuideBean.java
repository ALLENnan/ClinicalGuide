package com.allen.guide.model.entities;

import java.io.Serializable;

public class GuideBean implements Serializable {
    private int id;
    private String title;
    private String author;
    private String source;
    private String abstract_cn;
    private String file;
    private int favour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAbstract_cn() {
        return abstract_cn;
    }

    public void setAbstract_cn(String abstract_cn) {
        this.abstract_cn = abstract_cn;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getFavour() {
        return favour;
    }

    public void setFavour(int favour) {
        this.favour = favour;
    }

    @Override
    public String toString() {
        return "GuideBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", source='" + source + '\'' +
                ", abstract_cn='" + abstract_cn + '\'' +
                ", file='" + file + '\'' +
                ", favour=" + favour +
                '}';
    }
}
