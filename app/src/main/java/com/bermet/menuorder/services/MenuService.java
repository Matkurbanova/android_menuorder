package com.bermet.menuorder.services;

import com.bermet.menuorder.data.Menu;
import com.bermet.menuorder.data.MenuResponse;
import com.bermet.menuorder.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MenuService {
    @GET("menu")
    Call<List<Menu>> getMenu();

    @GET("menu/menu/{type}")
    Call<List<Menu>> getMenuByType(@Path("type") int type);

    @POST("menu/add-to-cart/{pid}")
    Call<MenuResponse<Menu>> addToCart(@Path("pid") int pid, @Body User user);

}
