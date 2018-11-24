package com.nuller.developer.hall.main_recycle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuller.developer.hall.DetailsActivity;
import com.nuller.developer.hall.R;
import com.nuller.developer.hall.main_recycle.MainModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {


    private final Context context;
    private List<MainModel> mainModelList;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void sendDataToAdapter(List<MainModel> mainModelList) {
        this.mainModelList = mainModelList;
    }



    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_list, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        MainModel mainModel = mainModelList.get(position);
        holder.name.setText(mainModel.getName());
        holder.salary.setText(mainModel.getSalary());
        holder.city.setText(mainModel.getCity());
        holder.rate.setText(mainModel.getRate());



        Picasso.get().load(mainModel.getImg()).placeholder(R.drawable.default1).into(holder.img);



    }

    @Override
    public int getItemCount() {
        return mainModelList != null ? mainModelList.size() : 0;
    }




    class MainViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name , salary , city , rate;
        Typeface myFont;


        public MainViewHolder(final View itemView) {
            super(itemView);

            myFont = Typeface.createFromAsset(context.getAssets(),"fonts/JannaLT-Regular.ttf");
            img = itemView.findViewById(R.id.main_img_list);
            name = itemView.findViewById(R.id.main_name_list);
            name.setTypeface(myFont);
            salary = itemView.findViewById(R.id.main_salary_list);
            city = itemView.findViewById(R.id.main_city);
            rate = itemView.findViewById(R.id.main_rate);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context,DetailsActivity.class);
                    int adapterPosition = getAdapterPosition();
                    MainModel mainModel = mainModelList.get(adapterPosition);
                    i.putExtra("info",mainModel);
                    context.startActivity(i);

                }
            });

        }
    }

}
