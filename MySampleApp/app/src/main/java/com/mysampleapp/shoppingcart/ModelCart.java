package com.mysampleapp.shoppingcart;

import java.util.ArrayList;

/**
 * Created by root on 5/26/17.
 */

class ModelCart {
    private ArrayList<ModelProducts> cartItems = new ArrayList<ModelProducts>();
    public ModelProducts getProducts(int position){
        return cartItems.get(position);
    }

    public void setProducts(ModelProducts Products){

        cartItems.add(Products);
    }

    public int getCartsize(){

        return cartItems.size();
    }

    public boolean CheckProductInCart(ModelProducts aproduct){

        return cartItems.contains(aproduct);
    }
}
