package com.bermet.menuorder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bermet.menuorder.R;
import com.bermet.menuorder.Statics;
import com.bermet.menuorder.data.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuVH>{
    private Context context;
    private List<Menu>menu=new ArrayList<>();

    private AdapterListener adapterListener;

    public MenuAdapter(Context context){
        this.context=context;
        if (context instanceof AdapterListener)
            adapterListener = (AdapterListener) context;


    }
    public void notifyListener(Menu menu){
        if (adapterListener != null)
            adapterListener.onMenuClick(menu);
    }

    public void setMenu(List<Menu>menu){
        this.menu.clear();
        this.menu.addAll(menu);
        notifyDataSetChanged();



    }
    @NonNull
    @Override
    public MenuVH onCreateViewHolder(@NonNull ViewGroup viewGroup,int i){
        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_menu,viewGroup,false);
        return new MenuVH(view);

    }
    @Override
    public void onBindViewHolder(@NonNull MenuVH menuVH,int i){
        final Menu menus = menu.get(i);
        menuVH.textViewName.setText(menus.name);
        menuVH.textViewDescription.setText(menus.description);
        menuVH.textViewPrice.setText(String.valueOf(menus.price));
        menuVH.textViewAmount.setText(menus.amount);
        menuVH.textViewId.setText(String.valueOf(menus.id));
        menuVH.textViewType.setText(String.valueOf(menus.type));

        if(menus.image!=null&&!menus.image.isEmpty());
        Picasso.get()
                .load(Statics.URL_IMAGES + menus.image)
                .into(menuVH.imageView);
        menuVH.imageButtonAddToCart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
notifyListener(menus);
                    }
                }
        );


    }
    @Override
    public int getItemCount() {
        return menu.size();
    }
    public interface AdapterListener{
        void onMenuClick(Menu menu);
    }
    class MenuVH extends RecyclerView.ViewHolder{
        TextView textViewName;
        ImageView imageView;
        TextView textViewDescription;
        TextView textViewPrice;

        TextView textViewAmount;
        TextView textViewType;
        TextView textViewId;
        ImageButton imageButtonAddToCart;

        public MenuVH(View view){
            super(view);
            textViewName=view.findViewById(R.id.textViewName);
             imageView=view.findViewById(R.id.imageView);
             textViewDescription=view.findViewById(R.id.textViewDescription);
            textViewPrice = view.findViewById(R.id.textViewPrice);
            textViewAmount = view.findViewById(R.id.textViewAmount);
            textViewType=view.findViewById(R.id.textViewType);
            textViewId = view.findViewById(R.id.textViewId);
            imageButtonAddToCart = view.findViewById(R.id.ImageButtonAddToCard);

        }
    }
}
