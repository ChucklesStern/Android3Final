package com.mysampleapp.shoppingcart;

import com.mysampleapp.Application;

import java.util.ArrayList;

/**
 * Created by root on 5/26/17.
 */

public class Controller extends Application {
    private ArrayList<ModelProducts> myproducts = new ArrayList<ModelProducts>();

    private ModelCart myCart = new ModelCart();

    public ModelProducts getProducts(int pPosition) {

        return myproducts.get(pPosition);
    }

    public void setProducts(ModelProducts products) {

        myproducts.add(products);
    }

    public ModelCart getCart() {

        return myCart;
    }

    public int getProductArraylistSize(){

        return myproducts.size();
    }
}
