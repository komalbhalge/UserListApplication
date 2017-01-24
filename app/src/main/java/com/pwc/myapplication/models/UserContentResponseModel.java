package com.pwc.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mac_admin on 23/01/17.
 */

public class UserContentResponseModel {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("rows")
        @Expose
        private ArrayList<Row> rows = null;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<Row> getRows() {
            return rows;
        }

        public void setRows(ArrayList<Row> rows) {
            this.rows = rows;
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
        private String imageHref;

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

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }

    }
}
