package com.bermet.menuorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bermet.menuorder.Statics;
import com.bermet.menuorder.adapters.OrdersAdapter;
import com.bermet.menuorder.data.Menu;
import com.bermet.menuorder.data.Order;
import com.bermet.menuorder.services.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersActivity  extends AppCompatActivity {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Statics.URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build();


    OrderService orderService = retrofit.create(OrderService.class);
    RecyclerView recyclerView;
    OrdersAdapter ordersAdapter;

    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        ordersAdapter = new OrdersAdapter(this);

        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ordersAdapter);




        loadOrders();

    }
    void loadOrders() {
        long userId = Prefs.getLong("id", -1);

        Call<List<Order>> call = orderService.getOrders(userId);

        call.enqueue(new Callback<List<Order>>() {

            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful())
                    ordersAdapter.setOrders(response.body());

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("Main", "onFailure", t);

            }
        });

    }

    }


