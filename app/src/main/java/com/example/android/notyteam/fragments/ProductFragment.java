package com.example.android.notyteam.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.notyteam.adapters.DetailsTabAdapter;
import com.example.android.notyteam.adapters.ImagesPagerAdapter;
import com.example.android.notyteam.parsingdata.Product;
import com.example.android.notyteam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private ImageView productImage;
    private TextView productPrice;
    private TextView productName;
    public TextView imagePageCount;
    private ViewPager imagePager;
    private ImagesPagerAdapter imagesPagerAdapter;
    private ViewPager detailsPager;
    private DetailsTabAdapter detailsAdapter;

    private int productId;
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");
            productId = getArguments().getInt("productId");
        }
        productImage = view.findViewById(R.id.product_image);
        productPrice = view.findViewById(R.id.product_price_tv);
        productName = view.findViewById(R.id.product_name_tv);
        imagePageCount = view.findViewById(R.id.page_count);
        imagePager = view.findViewById(R.id.pager);
        detailsPager = view.findViewById(R.id.details_pager);

        imagesPagerAdapter = new ImagesPagerAdapter(getFragmentManager(), product);
        imagePager.setAdapter(imagesPagerAdapter);
        imagePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                imagePageCount.setText(pageCountFormater(position));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        detailsAdapter = new DetailsTabAdapter(getFragmentManager(), product);
        detailsPager.setAdapter(detailsAdapter);

        setUpUi();
        return view;
    }

    private void setUpUi() {
        productPrice.setText(product.getPrice() + " руб.");
        productName.setText(product.getName());
    }

    private String pageCountFormater(int position){
        String pages;
            pages = position+1 + "/" +  product.getImages().size();
        return pages;
    }


}
