package com.example.chanzyhebat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chanzyhebat.Model.ProductRequest;
import com.example.chanzyhebat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterJava extends RecyclerView.Adapter<AdapterJava.HolderData>{
    private Context ctx;
    private List<ProductRequest> productRequests;

    public AdapterJava(Context ctx, List<ProductRequest> productRequests) {
        this.ctx = ctx;
        this.productRequests = productRequests;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ProductRequest pr = productRequests.get(position);
        holder.tvname.setText(pr.getName());
        holder.tvprice.setText(pr.getPrice());
        holder.tvrating.setText(pr.getPrice());
        Picasso.get().load(pr.getImage()).fit().centerCrop().into(holder.imgbrg);
    }

    @Override
    public int getItemCount() {
        return productRequests.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvname,tvrating,tvprice;
        ImageView imgbrg;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tv_namabrg);
            tvrating = itemView.findViewById(R.id.tv_rating);
            tvprice = itemView.findViewById(R.id.tv_harga);
            imgbrg = itemView.findViewById(R.id.img_brg);
        }
    }
}
