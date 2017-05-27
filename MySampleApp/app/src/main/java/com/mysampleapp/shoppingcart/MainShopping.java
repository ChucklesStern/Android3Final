package com.mysampleapp.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobilehelper.auth.IdentityManager;
import com.mysampleapp.R;
import com.mysampleapp.demo.DemoFragmentBase;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MainShopping extends DemoFragmentBase {
    /** The identity manager used to keep track of the current user account. */
    private IdentityManager identityManager;

    /** This fragment's view. */
    private View mFragmentView;

    /** Text view for showing the user identity. */
    private TextView userIdTextView;

    /** Text view for showing the user name. */
    private TextView userNameTextView;

    /** Image view for showing the user image. */
    private ImageView userImageView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mFragmentView = inflater.inflate(R.layout.activity_main_shopping, container, false);

      //  userNameTextView = (TextView) mFragmentView.findViewById(R.id.textView_demoIdentityUserName);
      //  userIdTextView = (TextView) mFragmentView.findViewById(R.id.textView_demoIdentityUserID);
      //  userImageView = (ImageView)mFragmentView.findViewById(R.id.imageView_demoIdentityUserImage);

        // Obtain a reference to the identity manager.
        /*
        identityManager = AWSMobileClient.defaultMobileClient()
                .getIdentityManager();
        identityManager.addSignInStateChangeListener(this);
        fetchUserIdentity();
        */
        return mFragmentView;
    }

public class MainShopping2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mFragmentView);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linearMain);
        final Button btn = (Button) findViewById(R.id.second);
        final Controller ct = (Controller) getApplicationContext();
        ModelProducts products = null;
        for (int i = 1; i <= 7; i++) {
            int Price = 15 + i;
            products = new ModelProducts("Product Item" + i, "Description" + i, Price);
            ct.setProducts(products);
        }
        int productsize = ct.getProductArraylistSize();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int j = 0; j < productsize; j++) {
            String pName = ct.getProducts(j).getProductName();
            int pPrice = ct.getProducts(j).getproductPrice();
            LinearLayout la = new LinearLayout(this);
            la.setOrientation(LinearLayout.HORIZONTAL);
            TextView tv = new TextView(this);
            tv.setText(" " + pName + " ");
            la.addView(tv);
            final Button btn1 = new Button(this);
            btn1.setId(j + 1);
            btn1.setText("Add to Cart");
            btn1.setLayoutParams(params);
            final int index = j;
            btn1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG", "index:" + index);
                    ModelProducts productsObject = ct.getProducts(index);
                    if (!ct.getCart().CheckProductInCart(productsObject)) {
                        btn.setText("Item Added");
                        ct.getCart().setProducts(productsObject);
                        Toast.makeText(getApplicationContext(), "New CartSize:" + ct.getCart().getCartsize(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Products" + (index + 1) + "Already Added", Toast.LENGTH_LONG).show();
                    }

                }
            });
            la.addView(btn1);
            layout.addView(la);
        }
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent in = new Intent(getBaseContext(), screen2.class);
                startActivity(in);
            }
        });
    }
}
}
