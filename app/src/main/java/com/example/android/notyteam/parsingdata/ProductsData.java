package com.example.android.notyteam.parsingdata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsData {
    @SerializedName("data")
    private List<Product> productData;

    public List<Product> getProductData() {
        return productData;
    }
}
