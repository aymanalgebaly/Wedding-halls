package com.nuller.developer.hall.offers_recycle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuller.developer.hall.OffersActivity;
import com.nuller.developer.hall.R;
import com.nuller.developer.hall.main_recycle.MainModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.offersViewHolder> {


    private final Context context;
    private List<MainModel> offerModelList;

    public OffersAdapter(Context context) {
        this.context = context;
    }


    public void sendDataToAdapter(List<MainModel> offerModelList) {
        this.offerModelList = offerModelList;
    }



    @NonNull
    @Override
    public offersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.offers_list, parent, false);

        return new offersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull offersViewHolder holder, int position) {
        MainModel offersModel = offerModelList.get(position);
        holder.hollName.setText(offersModel.getName());
        holder.hollDescound.setText(offersModel.getDescound());

        Picasso.get().load(offersModel.getImg()).placeholder(R.drawable.default1).into(holder.offerImage);

    }

    @Override
    public int getItemCount() {
        return offerModelList !=null ? offerModelList.size() : 0;
    }



    class offersViewHolder extends RecyclerView.ViewHolder {

        ImageView offerImage;
        TextView hollName , hollDescound;

        public offersViewHolder(View itemView) {
            super(itemView);

            offerImage = itemView.findViewById(R.id.offer_img);
            hollName = itemView.findViewById(R.id.offer_holl_name);
            hollDescound=itemView.findViewById(R.id.offer_descound);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent i = new Intent(context,OffersActivity.class);
                    int adapterPosition = getAdapterPosition();
                    MainModel offersModel =offerModelList.get(adapterPosition);
                    i.putExtra("offer",offersModel);
                    context.startActivity(i);



                }
            });

        }
    }

}
