package com.allen.guide.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Allen on 16/10/5.
 */

public class JFile {
    private int total;

    private List<File> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<File> getRows() {
        return rows;
    }

    public void setRows(List<File> rows) {
        this.rows = rows;
    }

    public static class File implements Serializable {
        private int id;
        private String title;
        private String author;
        private String source;
        private String desc;
        private String file;

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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }
}
