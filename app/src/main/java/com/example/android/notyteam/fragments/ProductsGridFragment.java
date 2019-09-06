package com.example.android.notyteam.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.notyteam.adapters.GridRecyclerViewAdapter;
import com.example.android.notyteam.parsingdata.Product;
import com.example.android.notyteam.parsingdata.ProductsData;
import com.example.android.notyteam.R;
import com.example.android.notyteam.api.WebApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductsGridFragment extends Fragment {
    private int productId;
    GridRecyclerViewAdapter gridAdapter;
    RecyclerView grid_rv;
    OnGridFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_grid, container, false);
        if (getArguments() != null)
            productId = getArguments().getInt("clickedItemId");
        gridAdapter = new GridRecyclerViewAdapter(mListener);
        grid_rv = view.findViewById(R.id.grid_recycler_view);
        grid_rv.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        grid_rv.setAdapter(gridAdapter);
        networkCall();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnGridFragmentInteractionListener) context;

    }

    public interface OnGridFragmentInteractionListener {
        void onGridFragmentInteraction(Product item);
    }

    private void networkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebApi.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebApi webApi = retrofit.create(WebApi.class);
        Call<ProductsData> productsDataCall = webApi.getCategoriesListByIdPagePerPage(productId);
        productsDataCall.enqueue(new Callback<ProductsData>() {
            @Override
            public void onResponse(Call<ProductsData> call, Response<ProductsData> response) {
                List<Product> products = response.body().getProductData();
                gridAdapter.setData(products);
            }

            @Override
            public void onFailure(Call<ProductsData> call, Throwable t) {
                Log.v("Grid fragment call failure", t.getMessage());
            }
        });

    }

}
