package com.issam.remoteunity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


import static com.android.volley.VolleyLog.TAG;


public class recyclerAdapterDataFromSrv extends RecyclerView.Adapter<recyclerAdapterDataFromSrv.MyViewHolder> {
    ArrayList<Repos> arrayList = new ArrayList<Repos>() ;
    private Context context ;

    public recyclerAdapterDataFromSrv(ArrayList<Repos> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public recyclerAdapterDataFromSrv(ArrayList<Repos> arrayList){
        this.arrayList=arrayList;
    }
    @Override
    public MyViewHolder  onCreateViewHolder( ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ArrayList<Repos> ListPro = new ArrayList<Repos>();
        Log.d(TAG, "onBindViewHolder: appllé");
        Picasso.get().load(arrayList.get(position).getAvatarURL()).into(holder.iv);
        holder.txt_repos_name.setText(arrayList.get(position).getReposName());
        holder.txt_repos_desc.setText(arrayList.get(position).getReposDescription());
        holder.txt_username.setText(arrayList.get(position).getUserName());
        holder.txt_score.setText(arrayList.get(position).getStarsNbr());

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_repos_name,txt_repos_desc,txt_username,txt_score;
        ImageView iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.avatar_user);
            txt_repos_name = (TextView) itemView.findViewById(R.id.txt_repos_name);
            txt_repos_desc = (TextView) itemView.findViewById(R.id.txt_repos_desc);
            txt_username = (TextView) itemView.findViewById(R.id.txt_username);
            txt_score = (TextView) itemView.findViewById(R.id.txt_score);
        }

    }
}
