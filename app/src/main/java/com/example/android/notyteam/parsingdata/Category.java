package com.example.android.notyteam.parsingdata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    private int id;
    private String name;
    @SerializedName("has_sub_categories")
    private boolean hasSubCategories;
    @SerializedName("sub_categories")
    private List<Category> subCategories;

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isHasSubCategories() {
        return hasSubCategories;
    }
}
