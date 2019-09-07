package com.example.android.notyteam.parsingdata;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
public class Product implements Serializable {
    private int id;
    private String name;
    private int price;
    private List<String> images;
    private String description;
    @SerializedName("details")
    private ProductDetails productDetails;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    static public class ProductDetails implements Serializable{
        @SerializedName("Вес")
        private String weigth;
        @SerializedName("Ширина упаковки")
        private String package_width;
        @SerializedName("Высота упаковки")
        private String package_height;
        @SerializedName("Глубина упаковки")
        private String package_depth;
        @SerializedName("crossborder")
        private String crossborder;
        @SerializedName("Тип")
        private String type;
        @SerializedName("Цвет")
        private String color;
        @SerializedName("ebsmstock")
        private String ebsmstock;

        public String getWeigth() {
            return weigth;
        }

        public String getPackage_width() {
            return package_width;
        }

        public String getPackage_height() {
            return package_height;
        }

        public String getPackage_depth() {
            return package_depth;
        }

        public String getCrossborder() {
            return crossborder;
        }

        public String getType() {
            return type;
        }

        public String getColor() {
            return color;
        }

        public String getEbsmstock() {
            return ebsmstock;
        }
    }
}
