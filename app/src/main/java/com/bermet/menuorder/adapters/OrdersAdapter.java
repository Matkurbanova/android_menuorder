package com.bermet.menuorder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bermet.menuorder.R;
import com.bermet.menuorder.data.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersVH> {
    private Context context;
    private List<Order> orders = new ArrayList<>();

    public OrdersAdapter(Context context) {
        this.context = context;
    }

    public void setOrders(List<Order> orders) {
        this.orders.clear();
        this.orders.addAll(orders);
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public OrdersVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_order, viewGroup, false);


        return new OrdersVH(view);


    }
    @Override
    public void onBindViewHolder(@NonNull OrdersVH ordersVH, int i) {
        final Order order = orders.get(i);
        ordersVH.textViewId.setText(String.valueOf(order.id));
        ordersVH.textViewUserId.setText(String.valueOf(order.userId));
        ordersVH.textViewOrderdate.setText(String.valueOf(order.orderdate));
        ordersVH.textViewMenuId.setText(String.valueOf(order.menuId));
        ordersVH.textViewComment.setText(String.valueOf(order.comment));
        ordersVH.textViewTime.setText(order.time);
        ordersVH.textViewCount.setText(String.valueOf(order.count));

        ordersVH.textViewStatus.setText(String.valueOf(order.status));

    }
    @Override
    public int getItemCount() {

        return orders.size();
    }

    class OrdersVH extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewOrderdate;
        TextView textViewUserId;
        TextView textViewMenuId;
        TextView textViewComment;
        TextView textViewTime;
        TextView textViewCount;
        TextView textViewStatus;


        public OrdersVH(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewOrderdate = itemView.findViewById(R.id.textViewOrderdate);
            textViewMenuId = itemView.findViewById(R.id.textViewMenuId);
            textViewComment = itemView.findViewById(R.id.textViewComment);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewCount = itemView.findViewById(R.id.textViewCount);
            textViewUserId=itemView.findViewById(R.id.textViewUserId);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);

        }
    }
}

