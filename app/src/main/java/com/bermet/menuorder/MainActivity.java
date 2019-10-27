package com.bermet.menuorder;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.bermet.menuorder.adapters.MenuAdapter;
import com.bermet.menuorder.data.Menu;
import com.bermet.menuorder.data.MenuResponse;
import com.bermet.menuorder.data.User;
import com.bermet.menuorder.services.MenuService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MenuAdapter.AdapterListener {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Statics.URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build();
    MenuService menuService = retrofit.create(MenuService.class);
    RecyclerView recyclerView;
    MenuAdapter menuAdapter;
    ActionBar actionBar;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        menuAdapter = new MenuAdapter(this);
        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(menuAdapter);

        Intent intent = getIntent();

        if (intent != null && intent.getExtras().containsKey("type")) {
            type = intent.getExtras().getInt("type");
            Log.i("MainActivity", "onCreate: " + type);
        }

        loadMenu();
    }

    void loadMenu() {
        Call<List<Menu>> call = type > 0 ? menuService.getMenuByType(type) : menuService.getMenu();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if (response.isSuccessful())
                    menuAdapter.setMenu(response.body());
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e("Main", "onFailure", t);
            }
        });

    }

    @Override
    public void onMenuClick(Menu menu) {
        if (Prefs.getBoolean("is_logged_in", false)) {
            Toast.makeText(this, "add" + menu.id, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, Statics.ACTION_LOGIN);

        }
    }

    private void addToCart(Menu menu) {
        Call<MenuResponse<Menu>> call = menuService.addToCart(menu.id, getUser());
        call.enqueue(new Callback<MenuResponse<Menu>>() {
            @Override
            public void onResponse(Call<MenuResponse<Menu>> call, Response<MenuResponse<Menu>> response) {

            }


            @Override
            public void onFailure(Call<MenuResponse<Menu>> call, Throwable t) {

            }
        });
    }

    private User getUser() {
        if (Prefs.getLong("id", -1) > 0) {
            User user = new User();
            user.id = Prefs.getLong("id", -1);
            user.login = Prefs.getString("login", "");
            return user;

        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case Statics.ACTION_LOGIN:
                if (requestCode == Statics.ACTION_LOGGED_IN) {
                    String name = data.getStringExtra("name");
                    actionBar.setTitle(Prefs.getString("name", "Menu"));
                    Toast.makeText(this, "Вы авторизованы", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                Intent intent = new Intent(this, OrdersActivity.class);
                startActivityForResult(intent, Statics.ACTION_LOGIN);
                return true;
        }
        return super.onOptionsItemSelected(item);}}

