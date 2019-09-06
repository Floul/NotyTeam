package com.example.android.notyteam.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.notyteam.utils.DownloadImageTask;
import com.example.android.notyteam.parsingdata.Product;
import com.example.android.notyteam.R;
import com.example.android.notyteam.parsingdata.SingleProductData;
import com.example.android.notyteam.api.WebApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    ImageView productImage;
    TextView productPrice;
    TextView productDescription;

    private int productId;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        if (getArguments() != null)
            productId = getArguments().getInt("productId");
        productImage = view.findViewById(R.id.product_image);
        productPrice = view.findViewById(R.id.product_price_tv);
        productDescription =view.findViewById(R.id.product_description_tv);
                networkCall();
        return view;
    }

    private void networkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebApi.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebApi webApi = retrofit.create(WebApi.class);
        Call<SingleProductData> productCall = webApi.getGoodsById(productId);
        productCall.enqueue(new Callback<SingleProductData>() {
            @Override
            public void onResponse(Call<SingleProductData> call, Response<SingleProductData> response) {
                Product product = response.body().getData();
                setUpUi(product);
                Log.v("asdf", "asdf");
            }

            @Override
            public void onFailure(Call<SingleProductData> call, Throwable t) {

            }
        });
    }

    private void setUpUi(Product product) {
        productPrice.setText(Integer.toString(product.getPrice()));
        productDescription.setText(product.getDescription());
        new DownloadImageTask(productImage).execute(product.getImages().get(0));
    }

}
