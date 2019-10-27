package com.bermet.menuorder.services;

import com.bermet.menuorder.data.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService {
@GET("orders/{user_id}")
    Call<List<Order>>getOrders(@Path("user_id")long UserId);

}
