package com.example.tugasakhir.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.TugasAkhir.Helper.TinyDB;
import com.example.tugasakhir.Domain.FoodDomain;
import com.example.tugasakhir.Interface.ChangeNumberitemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private com.example.TugasAkhir.Helper.TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB =new TinyDB(context);
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood=getListCart();
        boolean existAlready=false;
        int n =0;
        for (int i =0; i < listFood.size(); i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if (existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }

        tinyDB.putListObject("CardList",listFood);
        Toast.makeText(context, "Addd to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CardList");

    }
    public void minusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberitemsListener changeNumberitemsListener){
        if(listfood.get(position).getNumberInCart() == 1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() -1);
        }
        tinyDB.putListObject("Cardil", listfood);
        changeNumberitemsListener.changed();
    }
    public void plusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberitemsListener changeNumberitemsListener){
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CardList", listfood);
        changeNumberitemsListener.changed();
    }
    public Double getTotalFee(){
        ArrayList<FoodDomain>listfood2=getListCart();
        double fee=0;
        for (int i = 0; i < listfood2.size(); i++){
            fee=fee+(listfood2.get(i).getFee()*listfood2.get(i).getNumberInCart());
        }
        return fee;
    }
}
