package com.example.android.notyteam.api;

import com.example.android.notyteam.parsingdata.CatalogData;
import com.example.android.notyteam.parsingdata.ProductsData;
import com.example.android.notyteam.parsingdata.SingleProductData;
import com.example.android.notyteam.parsingdata.SubCatalogData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebApi {
    String SERVER_URL = "https://api.aist.im/api/v1/";

    @Headers({
            "X-Auth-Id: 1",
            "X-Session-Id: 922803673",
            "X-Locale: \"ru\""
    }
    )
    @GET("catalog/categories")
    Call<CatalogData> getCategoriesList();

    @Headers({
            "X-Auth-Id: 1",
            "X-Session-Id: 922803673",
            "X-Locale: \"ru\""
    }
    )
    @GET("catalog/categories/{category_id}")
    Call<SubCatalogData> getCategoriesListById(@Path("category_id") int categoryId);

    @Headers({
            "X-Auth-Id: 1",
            "X-Session-Id: 922803673",
            "X-Locale: \"ru\""
    }
    )
    @GET("catalog/categories/{category_id}/products?page=1&per_page=500")
    Call<ProductsData> getCategoriesListByIdPagePerPage(@Path("category_id") int categoryId);

    @Headers({
            "X-Auth-Id: 1",
            "X-Session-Id: 922803673",
            "X-Locale: \"ru\""
    }
    )
    @GET("catalog/products/{product_id}")
    Call<SingleProductData> getGoodsById(@Path("product_id") int categoryId);

    @Headers({
            "X-Auth-Id: 1",
            "X-Session-Id: 922803673",
            "X-Locale: \"ru\""
    }
    )
    @GET("catalog/products/search" + "?page=1&per_page=500")
    List getSearchList(@Query("query") String subject);


}
