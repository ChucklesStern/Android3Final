package com.mysampleapp.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mysampleapp.R;

public class screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        TextView showCartContent = (TextView)findViewById(R.id.showCart);
        final Controller ct = (Controller)getApplicationContext();
        final int CartSize = ct.getCart().getCartsize();
        String show = "";
        for(int i=0; i<CartSize;i++){
            String pName = ct.getCart().getProducts(i).getProductName();
            Integer pPrice = ct.getCart().getProducts(i).getproductPrice();
            String pDesc = ct.getCart().getProducts(i).getProductDesc();
            show += "Product Name:"+pName+",Product Price:"+pPrice+", Product Description: "+pDesc+""+"-----";
        }
        showCartContent.setText(show);
    }
}
