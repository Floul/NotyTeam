package com.example.android.notyteam.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.android.notyteam.parsingdata.CatalogData;
import com.example.android.notyteam.parsingdata.Category;
import com.example.android.notyteam.adapters.MyCatalogRecyclerViewAdapter;
import com.example.android.notyteam.R;
import com.example.android.notyteam.parsingdata.SubCatalogData;
import com.example.android.notyteam.api.WebApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CatalogFragment extends Fragment {

    static final String TAG = "Catalog Fragment";
    private OnListFragmentInteractionListener mListener;
    private MyCatalogRecyclerViewAdapter listAdapter;
    private int clickedItemId;

    public CatalogFragment() {
    }

    @SuppressWarnings("unused")
    public static CatalogFragment newInstance(int columnCount) {
        CatalogFragment fragment = new CatalogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_list, container, false);
        if (getArguments() != null) {
            clickedItemId = this.getArguments().getInt("clickedItemId");

        }
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            listAdapter = new MyCatalogRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(listAdapter);
        }

        networkCall();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            mListener = (OnListFragmentInteractionListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Category item);
    }

    private void networkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebApi.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebApi webApi = retrofit.create(WebApi.class);
        if (clickedItemId == 0) {
            Log.v(TAG, "catalog fragment");
            Call<CatalogData> categoriesCall = webApi.getCategoriesList();
            categoriesCall.enqueue(new Callback<CatalogData>() {
                @Override
                public void onResponse(Call<CatalogData> call, Response<CatalogData> response) {
                    List<Category> categories = response.body().getData();
                    listAdapter.setData(categories);

                }

                @Override
                public void onFailure(Call<CatalogData> call, Throwable t) {
                    Log.v("Catalog Fragment", "Call Failure " + t.getMessage());

                }
            });
        } else {
            Call<SubCatalogData> categoriesByIdCall = webApi.getCategoriesListById(clickedItemId);
            categoriesByIdCall.enqueue(new Callback<SubCatalogData>() {

                @Override
                public void onResponse(Call<SubCatalogData> call, Response<SubCatalogData> response) {
                    List<Category> subCategories = response.body().getData().getSubCategories();
                    listAdapter.setData(subCategories);

                }

                @Override
                public void onFailure(Call<SubCatalogData> call, Throwable t) {
                    Log.v("Catalog Fragment", "Call failure " + t.getMessage());

                }
            });
        }
    }
}
