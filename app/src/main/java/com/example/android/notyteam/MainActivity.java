package com.example.android.notyteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.android.notyteam.fragments.CatalogFragment;
import com.example.android.notyteam.fragments.ProductFragment;
import com.example.android.notyteam.fragments.ProductsGridFragment;
import com.example.android.notyteam.parsingdata.Category;
import com.example.android.notyteam.parsingdata.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements CatalogFragment.OnListFragmentInteractionListener, ProductsGridFragment.OnGridFragmentInteractionListener {
    private final String TAG = "Main Activity";
    FrameLayout hostNavigationFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hostNavigationFragment = findViewById(R.id.nav_host_fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        default:
                            selectedFragment = new CatalogFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onListFragmentInteraction(Category item) {
        int clickedItemId = item.getId();
        Bundle args = new Bundle();
        args.putInt("clickedItemId", clickedItemId);
        if (item.isHasSubCategories()) {
            CatalogFragment catalogFragment = new CatalogFragment();
            catalogFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, catalogFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            ProductsGridFragment productsGridFragment = new ProductsGridFragment();
            productsGridFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, productsGridFragment)
                    .addToBackStack(null)
                    .commit();
        }


    }

    @Override
    public void onGridFragmentInteraction(Product item) {
        Bundle args = new Bundle();
        args.putInt("productId",item.getId());
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, productFragment)
                .addToBackStack(null)
                .commit();
        bottomNavigationView.setVisibility(BottomNavigationView.INVISIBLE);
    }
}
