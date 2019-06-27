package com.example.travelmate.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelmate.Model.DataModel;
import com.example.travelmate.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by praka on 12/24/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<DataModel> dataList;
    private Context context;
    private RecyclerItemClickListener recyclerItemClickListener;

    public CustomAdapter(Context context,List<DataModel> dataList,RecyclerItemClickListener recyclerItemClickListener){
        this.context = context;
        this.dataList = dataList;
        this.recyclerItemClickListener=recyclerItemClickListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle,title;
        TextView date;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title=mView.findViewById(R.id.titlenew);
            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
            date=mView.findViewById(R.id.date);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getPlace());
        holder.date.setText(dataList.get(position).getDate());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

