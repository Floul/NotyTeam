package com.example.android.notyteam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.notyteam.parsingdata.Product;
import com.example.android.notyteam.R;
import com.example.android.notyteam.fragments.ProductsGridFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.CardViewHolder> {

    private Context mContext;
    private List<Product> mData = new ArrayList<Product>();
    private ProductsGridFragment.OnGridFragmentInteractionListener mListener;

    public GridRecyclerViewAdapter(Context context, ProductsGridFragment.OnGridFragmentInteractionListener listener) {
        mListener = listener;
        mContext = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        holder.mItem = mData.get(position);
        holder.productName_tv.setText(mData.get(position).getName());
        holder.productPrice_tv.setText(Integer.toString(mData.get(position).getPrice()) + " руб");
        Picasso.with(mContext).load(holder.mItem.getImages().get(0)).into(holder.product_thumbNail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onGridFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Product> newData) {
        mData.clear();
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView product_thumbNail;
        TextView productName_tv;
        TextView productPrice_tv;
        Product mItem;
        View cardView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            productName_tv = itemView.findViewById(R.id.card_name);
            product_thumbNail = itemView.findViewById(R.id.card_thumbnail);
            productPrice_tv = itemView.findViewById(R.id.card_price);
            cardView = itemView;
        }
    }


}
