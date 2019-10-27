package com.bermet.menuorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bermet.menuorder.data.MenuResponse;
import com.bermet.menuorder.data.User;
import com.bermet.menuorder.services.UserService;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Statics.URL_API)

            .addConverterFactory(GsonConverterFactory.create())
            .build();
    UserService userService = retrofit.create(UserService.class);
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                buttonLogin.setEnabled(false);
                String login = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();
                if (login.isEmpty()) {
                    editTextLogin.setError("Введите логин");
                    return;
                }
                if (password.isEmpty()) {
                    editTextPassword.setError("Введите пароль");
                    return;
                }
                loginRequest(login, password);
                break;
        }
    }

    private void loginRequest(String lg, String pass) {
        retrofit2.Call<MenuResponse<User>> call = userService.login(new User(lg, pass));
        call.enqueue(new Callback<MenuResponse<User>>() {

            @Override
            public void onResponse(Call<MenuResponse<User>> call, retrofit2.Response<MenuResponse<User>> response) {
                buttonLogin.setEnabled(true);

                if (response.isSuccessful()) {
                    MenuResponse<User> userResponse = response.body();
                    if (userResponse.status == 1000) {
                        Prefs.putBoolean("is_logged_in", true);
                        Prefs.putLong("id", userResponse.data.id);

                        Intent intent = new Intent(LoginActivity.this, Food.class);
                        startActivity(intent);

                        finish();
                    } else {
                        editTextLogin.setError(userResponse.message);
                    }

                } else {
                    try {
                        Log.e("LOGIN", "onResponse:" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<MenuResponse<User>> call, Throwable t) {
                Log.e("LOGIN", "onFailure", t);
                buttonLogin.setEnabled(true);
            }
        });

    }

}











