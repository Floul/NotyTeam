package com.example.android.notyteam.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.notyteam.adapters.DetailsTabAdapter;
import com.example.android.notyteam.adapters.ImagesPagerAdapter;
import com.example.android.notyteam.parsingdata.Product;
import com.example.android.notyteam.R;
import com.example.android.notyteam.parsingdata.SingleProductData;
import com.example.android.notyteam.api.WebApi;
import com.google.android.material.tabs.TabLayout;

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
    TextView productName;
    ViewPager imagePager;
    ImagesPagerAdapter imagesPagerAdapter;
    ViewPager detailsPager;
    DetailsTabAdapter detailsAdapter;

    private int productId;
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        if (getArguments() != null) {
            product =(Product) getArguments().getSerializable("product");
            productId = getArguments().getInt("productId");
        }
        productImage = view.findViewById(R.id.product_image);
        productPrice = view.findViewById(R.id.product_price_tv);
        productName = view.findViewById(R.id.product_name_tv);
        imagePager = view.findViewById(R.id.pager);
        detailsPager = view.findViewById(R.id.details_pager);

        imagesPagerAdapter = new ImagesPagerAdapter(getFragmentManager(), product);
        imagePager.setAdapter(imagesPagerAdapter);
        detailsAdapter = new DetailsTabAdapter(getFragmentManager(), product);
        detailsPager.setAdapter(detailsAdapter);

        setUpUi();
        return view;
    }

    private void setUpUi() {
        productPrice.setText(Integer.toString(product.getPrice()) + " руб.");
        productName.setText(product.getName());
    }

}
