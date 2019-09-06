package com.example.android.notyteam.parsingdata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatalogData {
    @SerializedName("data")
    private List<Category> data;

    public List<Category> getData() {
        return data;
    }
}
