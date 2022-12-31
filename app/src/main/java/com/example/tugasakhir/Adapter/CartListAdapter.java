package com.example.tugasakhir.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugasakhir.Domain.FoodDomain;
import com.example.tugasakhir.Helper.ManagementCart;
import com.example.tugasakhir.Interface.ChangeNumberitemsListener;
import com.example.tugasakhir.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<FoodDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberitemsListener changeNumberitemsListener;

    public CartListAdapter(ArrayList<FoodDomain>listFoodSelected, Context context, ChangeNumberitemsListener changeNumberitemsListener){
        this.listFoodSelected = listFoodSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberitemsListener = changeNumberitemsListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart , parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("TAG", "Rp" + listFoodSelected.get(position).getFee());
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("Rp" + listFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("Rp" + Math.round(listFoodSelected.get(position).getNumberInCart()* listFoodSelected.get(position).getFee()));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));

        int drawableReourceId=holder.itemView.getContext().getResources()
                .getIdentifier(listFoodSelected.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.imgFoto);

        holder.plusItem.setOnClickListener(view -> managementCart.plusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberitemsListener.changed();
        }));

        holder.minusItem.setOnClickListener(view -> managementCart.minusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberitemsListener.changed();
        }));
    }

    @Override
    public int getItemCount() { return listFoodSelected.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView car_1, plusItem,minusItem,imgFoto;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            car_1 = itemView.findViewById(R.id.car_1);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
            imgFoto = itemView.findViewById(R.id.imgFoto);
        }
    }
}
