package com.pwc.myapplication.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mac_admin on 23/01/17.
 */

public class UserContentResponseClass {


    public class User {
/// http://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("rows")
        @Expose
        private List<Row> rows = null;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Row> getRows() {
            return rows;
        }

        public void setRows(List<Row> rows) {
            this.rows = rows;
        }

    }

    public class Row {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("imageHref")
        @Expose
        private Object imageHref;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getImageHref() {
            return imageHref;
        }

        public void setImageHref(Object imageHref) {
            this.imageHref = imageHref;
        }

    }
}
