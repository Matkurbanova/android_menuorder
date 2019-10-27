package com.bermet.menuorder.services;


import com.bermet.menuorder.data.MenuResponse;
import com.bermet.menuorder.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("user/login")
    Call<MenuResponse<User>> login(@Body User user );



}
