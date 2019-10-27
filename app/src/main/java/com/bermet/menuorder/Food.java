package com.bermet.menuorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Food extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSoups:
                Toast.makeText(this, "Soups", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("type", 3);
                this.startActivity(i);

                break;

            case R.id.buttonBreakfast:
                Toast.makeText(this, "Breakfast", Toast.LENGTH_SHORT).show();
                Intent m = new Intent(this, MainActivity.class);
                m.putExtra("type", 1);
                this.startActivity(m);
                break;
            case R.id.buttonSalads:
                Toast.makeText(this, "Salads", Toast.LENGTH_SHORT).show();
                Intent b = new Intent(this, MainActivity.class);
                b.putExtra("type", 2);
                this.startActivity(b);
                break;
            case R.id.buttonPizza:
                Toast.makeText(this, "Pizza", Toast.LENGTH_SHORT).show();
                Intent n = new Intent(this, MainActivity.class);
                n.putExtra("type", 5);
                this.startActivity(n);
                break;
            case R.id.buttonMeatDishes:
                Toast.makeText(this, "Meat Dishes", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(this, MainActivity.class);
                a.putExtra("type", 4);
                this.startActivity(a);
                break;

//                Intent
            case R.id.buttonFastFood:
                Toast.makeText(this, "Fast Food", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(this, MainActivity.class);
                c.putExtra("type", 7);
                this.startActivity(c);
                break;

            case R.id.buttonDessert:
                Toast.makeText(this, "Dessert", Toast.LENGTH_SHORT).show();
                Intent d = new Intent(this, MainActivity.class);
                d.putExtra("type", 8);
                this.startActivity(d);
                break;
            case R.id.buttonNationalСuisine:
                Toast.makeText(this, "National Cuisine", Toast.LENGTH_SHORT).show();
                Intent e = new Intent(this, MainActivity.class);
                e.putExtra("type", 9);
                this.startActivity(e);
                break;
            case R.id.buttonDrinks:
                Toast.makeText(this, "Drinks", Toast.LENGTH_SHORT).show();
                Intent f = new Intent(this, MainActivity.class);
                f.putExtra("type", 10);
                this.startActivity(f);
                break;
            case R.id.buttonSeaFood:
                Toast.makeText(this, "Sea Food", Toast.LENGTH_SHORT).show();
                Intent k = new Intent(this, MainActivity.class);
                k.putExtra("type", 6);
                this.startActivity(k);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //return super.onCreateOptionsMenu(menu);
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
        return super.onOptionsItemSelected(item);
}}
